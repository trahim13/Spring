package spring.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import spring.dao.interfaces.MP3Dao;
import spring.dao.objects.MP3;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

    private NamedParameterJdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void insert(MP3 mp3) {
        String sql = "insert into mp3 (name, author) VALUES (:name, author)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", mp3.getName());
        params.addValue("author", mp3.getAuthor());
        jdbcTemplate.update(sql, params);
    }


    @Override
    public void delete(int id) {
        String sql = "delete from mp3 where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        int resalt = jdbcTemplate.update(sql, params);

    }

    @Override
    public void insert(List<MP3> mp3List) {
        for (MP3 mp3 : mp3List) {
            insert(mp3);
        }
    }

    @Override
    public void delete(MP3 mp3) {
        // TODO Auto-generated method stub

    }

    @Override
    public MP3 getMP3ByID(int id) {
        String sql = "Select * from mp3 where id=:id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbcTemplate.queryForObject(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByName(String name) {
        String sql = "select * from mp3 where upper(name) like: name";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", "%" + name.toUpperCase() + "%");


        return jdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public List<MP3> getMP3ListByAuthor(String author) {
        String sql = "select * from mp3 where upper(author) like:author";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author", "%"+author.toUpperCase()+"%");

        return jdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public int getMP3Count() {
        String sql = "select count(*) from mp3";
        return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);
    }

    private static final class MP3RowMapper implements RowMapper<MP3> {
        @Override
        public MP3 mapRow(ResultSet resultSet, int i) throws SQLException {
            MP3 mp3 = new MP3();
            mp3.setName(resultSet.getString("name"));
            mp3.setAuthor(resultSet.getString("author"));
            return mp3;
        }
    }

}