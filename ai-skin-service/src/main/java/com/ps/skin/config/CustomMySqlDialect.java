package com.ps.skin.config;

import com.github.pagehelper.Page;
import com.github.pagehelper.dialect.helper.MySqlDialect;
import com.github.pagehelper.parser.CountSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义方言
 *
 * @author liuhj
 * @date 2020/05/18 20:48
 */
public class CustomMySqlDialect extends MySqlDialect {

    private static Logger logger = LoggerFactory.getLogger(CustomMySqlDialect.class);

    public static final String NEED_LIMIT = "/*count limit*/";

    /**
     * 原处理方式为父类的getCountSql
     * 处理方式为
     * 例如原sql: select id from task
     * 原处理方式后sql: select count(0) from task
     * 现处理方式后sql: select count(0) from (select id from task limit 1000) tmp_count
     *
     * @param ms
     * @param boundSql
     * @param parameterObject
     * @param rowBounds
     * @param countKey
     * @return
     */
    @Override
    public String getCountSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds, CacheKey countKey) {
        String sql = getCountLimtSql(boundSql);
        //解析SQL
        Statement stmt = null;
        //特殊sql不需要去掉order by时，使用注释前缀
        if (sql.contains(CountSqlParser.KEEP_ORDERBY)) {
            return countSqlParser.getSimpleCountSql(sql);
        }
        try {
            stmt = CCJSqlParserUtil.parse(sql);
        } catch (Throwable e) {
            //无法解析的用一般方法返回count语句
            return countSqlParser.getSimpleCountSql(sql);
        }
        Select select = (Select) stmt;
        SelectBody selectBody = select.getSelectBody();
        try {
            //处理body-去order by
            countSqlParser.processSelectBody(selectBody);
        } catch (Exception e) {
            //当 sql 包含 group by 时，不去除 order by
            return countSqlParser.getSimpleCountSql(sql);
        }
        //处理with-去order by
        countSqlParser.processWithItemsList(select.getWithItemsList());

        //自定义处理方式
        return countSqlParser.getSimpleCountSql(select.toString());
    }

    @Override
    public String getPageSql(String sql, Page page, CacheKey pageKey) {
        sql = sql.replace(NEED_LIMIT, " LIMIT 10000 ");
        return super.getPageSql(sql, page, pageKey);
    }

    private String getCountLimtSql(BoundSql boundSql) {
        String sql = boundSql.getSql().replace(NEED_LIMIT, " LIMIT 10000 ");
        StringBuilder sqlBuilder = new StringBuilder(sql);
        sqlBuilder.append(" LIMIT 10000 ");
        return sqlBuilder.toString();
    }

}
