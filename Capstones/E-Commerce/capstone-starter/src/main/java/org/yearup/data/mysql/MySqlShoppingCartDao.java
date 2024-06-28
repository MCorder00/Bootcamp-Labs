package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MySqlShoppingCartDao extends MySqlDaoBase implements ShoppingCartDao {

    @Autowired
    private ProductDao productDao;
    @Autowired
    public MySqlShoppingCartDao(DataSource dataSource, ProductDao productDao) {
        super(dataSource);
        this.productDao = productDao;
    }

    @Override
    public ShoppingCart getByUserId(int userId) {
        String sql = "{CALL GetShoppingCartItemsByUserId(?)}";
        ShoppingCart shoppingCart = new ShoppingCart();

        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ShoppingCartItem item = mapRowToShoppingCartItem(resultSet);
                shoppingCart.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return shoppingCart;
    }

    @Override
    public void addToCart(int userId, int productId) {
        String sql = "{CALL AddToCart(?, ?)}";

        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateCartItem(int userId, int productId, int quantity) {
        String sql = "{CALL UpdateCartItem(?, ?, ?)}";

        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, userId);
            statement.setInt(2, productId);
            statement.setInt(3, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearCart(int userId) {
        String sql = "{CALL ClearCart(?)}";

        try (Connection connection = getConnection()) {
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ShoppingCartItem mapRowToShoppingCartItem(ResultSet resultSet) throws SQLException {
       int productId = resultSet.getInt("product_id");
       int quantity = resultSet.getInt("quantity");

       Product product = productDao.getById(productId);

       ShoppingCartItem item = new ShoppingCartItem();
       item.setProduct(product);
       item.setQuantity(quantity);

       return item;
    }

}
