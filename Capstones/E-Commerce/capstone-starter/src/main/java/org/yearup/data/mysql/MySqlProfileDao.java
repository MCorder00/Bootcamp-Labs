package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yearup.models.Profile;
import org.yearup.data.ProfileDao;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class MySqlProfileDao extends MySqlDaoBase implements ProfileDao
{   @Autowired
    public MySqlProfileDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public Profile create(Profile profile)
    {
        String sql = "{CALL CreateProfile(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try(Connection connection = getConnection())
        {
            CallableStatement statement = connection.prepareCall(sql);

            statement.setString(1, profile.getFirstName());
            statement.setString(2, profile.getLastName());
            statement.setString(3, profile.getPhone());
            statement.setString(4, profile.getEmail());
            statement.setString(5, profile.getAddress());
            statement.setString(6, profile.getCity());
            statement.setString(7, profile.getState());
            statement.setString(8, profile.getZip());
            statement.setInt(9, profile.getUserId());

            statement.execute();

            return profile;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Profile getById(int userId) {
        String sql = "{CALL GetProfileById(?)}";
        try (Connection connection = getConnection())
        {
            CallableStatement statement = connection.prepareCall(sql);
            statement.setInt(1, userId);

            ResultSet row = statement.executeQuery();
            if (row.next())
            {
                return mapRow(row);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void update(int userId, Profile profile) {

        String sql = "{CALL UpdateProfile(?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection connection = getConnection())
        {
            CallableStatement statement = connection.prepareCall(sql);

            statement.setString(1, profile.getFirstName());
            statement.setString(2, profile.getLastName());
            statement.setString(3, profile.getPhone());
            statement.setString(4, profile.getEmail());
            statement.setString(5, profile.getAddress());
            statement.setString(6, profile.getCity());
            statement.setString(7, profile.getState());
            statement.setString(8, profile.getZip());
            statement.setInt(9, userId);

            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private Profile mapRow(ResultSet row) throws SQLException
    {
        int productId = row.getInt("user_id");
        String firstName = row.getString("first_name");
        String lastName = row.getString("last_name");
        String phone = row.getString("phone");
        String email = row.getString("email");
        String address = row.getString("address");
        String city = row.getString("city");
        String state = row.getString("state");
        String zip = row.getString("zip");
        return new Profile(productId, firstName, lastName, phone, email, address, city, state, zip);
    }
}
