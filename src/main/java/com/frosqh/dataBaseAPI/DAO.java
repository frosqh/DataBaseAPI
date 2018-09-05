package com.frosqh.dataBaseAPI;

import java.sql.*;

public class DAO<M extends Model> {

    private Connection connect;
    private Factory<M> factory;

    public DAO(ConnectionSQLite connection, Factory<M> fact){
        connect = connection.getInstance();
        factory = fact;
    }


    public M find(int id){
        try {
            Statement stm = connect.createStatement();
            String SELECT = "SELECT * FROM "+M.tableName+" WHERE id="+id;
            ResultSet resultSet = stm.executeQuery(SELECT);
            M model = factory.newItem();
            model.init(resultSet);
            stm.close();
            return model;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public M create(M obj){
        try {
            Statement stm = connect.createStatement();
            String select = "SELECT MAX(id) AS max_id FROM "+M.tableName; //Select *
            ResultSet result = stm.executeQuery(select);
            int id = 1;
            if (result.next()){
                id = result.getInt(1)+1;
            }
            stm.close();

            PreparedStatement prepare = this.connect.prepareStatement("INSERT INTO "+M.tableName+" (id, title, game_id, tags) VALUES (?,?,?,?)");
            prepare.setString(2,obj.getTitle());
            prepare.setString(3,obj.getGameId());
            if (obj.getTags() != null)
                prepare.setString(4,obj.getTags());

            prepare.executeUpdate();
            prepare.close();
            return find(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

