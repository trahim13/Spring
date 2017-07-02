package spring.dao.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import spring.dao.interfaces.MP3Dao;
import spring.dao.objects.MP3;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


@Component("sqliteDAO")
public class SQLiteDAO implements MP3Dao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private DataSource dataSource;
    private SimpleJdbcInsert insertMP3;


    @Autowired

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
            this.insertMP3 = new SimpleJdbcInsert(dataSource).withTableName("mp3").usingColumns("name", "author");
        this.dataSource = dataSource;
    }

    @Override
    public int insert(MP3 mp3) {
//        String sql = "insert into mp3 (name, author) VALUES (:name, :author)";
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("name", mp3.getName());
//        params.addValue("author", mp3.getAuthor());
//
//        jdbcTemplate.update(sql, params, keyHolder);
//
//        return keyHolder.getKey().intValue();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", mp3.getName());
        params.addValue("author", mp3.getAuthor());
        return insertMP3.execute(params);



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
    public int batchInsert(List<MP3> mp3List) {
//        String sql = "insert into mp3 (name, author) values(:name, :author)";
//        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(mp3List.toArray());
//
//        int[] updateCount = jdbcTemplate.batchUpdate(sql, batch);
//        return updateCount.length;

                String sql = "insert into mp3 (name, author) values(:name, :author)";
        SqlParameterSource[] params = new SqlParameterSource[mp3List.size()];
        int i = 0;

        for (MP3 mp3 : mp3List) {
            MapSqlParameterSource p = new MapSqlParameterSource();
            p.addValue("name", mp3.getName());
            p.addValue("author", mp3.getAuthor());

            params[i] = p;
            i++;
        }

        int[] updateCount = jdbcTemplate.batchUpdate(sql, params);
        return updateCount.length;




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
        params.addValue("author", "%" + author.toUpperCase() + "%");

        return jdbcTemplate.query(sql, params, new MP3RowMapper());
    }

    @Override
    public int getMP3Count() {
        String sql = "select count(*) from mp3";
        return jdbcTemplate.getJdbcOperations().queryForObject(sql, Integer.class);


    }

    @Override
    public Map<String, Integer> getStat() {
        String sql = "select author, count(*) as count from mp3 group by author";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Map<String, Integer>>() {
            @Override
            public Map<String, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<String, Integer> map = new TreeMap<>();

                while (resultSet.next()) {
                    String author = resultSet.getString("author");
                    int count = resultSet.getInt("count");
                    map.put(author, count);
                }

                return map;
            }
        });
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