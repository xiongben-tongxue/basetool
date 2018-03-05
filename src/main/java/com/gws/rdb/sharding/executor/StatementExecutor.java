/**
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.gws.rdb.sharding.executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.codahale.metrics.Timer.Context;
import com.gws.rdb.sharding.metrics.MetricsContext;

import lombok.RequiredArgsConstructor;

/**
 * 多线程执行静态语句对象请求的执行器.
 * 
 * @author gaohongtao
 */
@RequiredArgsConstructor
public final class StatementExecutor {
    
    private final ExecutorEngine executorEngine;
    
    private final Collection<StatementEntity> statements = new ArrayList<>();
    
    /**
     * 添加静态语句对象至执行上下文.
     * 
     * @param sql 转换后的SQL语句
     * @param statement 静态语句对象
     */
    public void addStatement(final String sql, final Statement statement) {
        statements.add(new StatementEntity(sql, statement));
    }
    
    /**
     * 执行SQL查询.
     * 
     * @return 结果集列表
     * @throws SQLException SQL异常
     */
    public List<ResultSet> executeQuery() throws SQLException {
        Context context = MetricsContext.start("ShardingStatement-executeQuery");
        List<ResultSet> result;
        if (1 == statements.size()) {
            StatementEntity entity = statements.iterator().next();
            result = Arrays.asList(entity.statement.executeQuery(entity.sql));
            MetricsContext.stop(context);
            return result;
        }
        result = executorEngine.execute(statements, new ExecuteUnit<StatementEntity, ResultSet>() {
            
            @Override
            public ResultSet execute(final StatementEntity input) throws Exception {
                return input.statement.executeQuery(input.sql);
            }
        });
        MetricsContext.stop(context);
        return result;
    }
    
    /**
     * 执行SQL更新.
     * 
     * @return 更新数量
     * @throws SQLException SQL异常
     */
    public int executeUpdate() throws SQLException {
        return executeUpdate(new Updater() {
            
            @Override
            public int executeUpdate(final Statement statement, final String sql) throws SQLException {
                return statement.executeUpdate(sql);
            }
        });
    }
    
    public int executeUpdate(final int autoGeneratedKeys) throws SQLException {
        return executeUpdate(new Updater() {
            
            @Override
            public int executeUpdate(final Statement statement, final String sql) throws SQLException {
                return statement.executeUpdate(sql, autoGeneratedKeys);
            }
        });
    }
    
    public int executeUpdate(final int[] columnIndexes) throws SQLException {
        return executeUpdate(new Updater() {
            
            @Override
            public int executeUpdate(final Statement statement, final String sql) throws SQLException {
                return statement.executeUpdate(sql, columnIndexes);
            }
        });
    }
    
    public int executeUpdate(final String[] columnNames) throws SQLException {
        return executeUpdate(new Updater() {
            
            @Override
            public int executeUpdate(final Statement statement, final String sql) throws SQLException {
                return statement.executeUpdate(sql, columnNames);
            }
        });
    }
    
    private int executeUpdate(final Updater updater) throws SQLException {
        Context context = MetricsContext.start("ShardingStatement-executeUpdate");
        int result;
        if (1 == statements.size()) {
            StatementEntity entity = statements.iterator().next();
            result = updater.executeUpdate(entity.statement, entity.sql);
            MetricsContext.stop(context);
            return result;
        }
        result = executorEngine.execute(statements, new ExecuteUnit<StatementEntity, Integer>() {
            
            @Override
            public Integer execute(final StatementEntity input) throws Exception {
                return updater.executeUpdate(input.statement, input.sql);
            }
        }, new MergeUnit<Integer, Integer>() {
            
            @Override
            public Integer merge(final List<Integer> results) {
                int result = 0;
                for (int each : results) {
                    result += each;
                }
                return result;
            }
        });
        MetricsContext.stop(context);
        return result;
    }
    
    /**
     * 执行SQL请求.
     * 
     * @return true表示执行DQL语句, false表示执行的DML语句
     * @throws SQLException SQL异常
     */
    public boolean execute() throws SQLException {
        return execute(new Executor() {
            
            @Override
            public boolean execute(final Statement statement, final String sql) throws SQLException {
                return statement.execute(sql);
            }
        });
    }
    
    public boolean execute(final int autoGeneratedKeys) throws SQLException {
        return execute(new Executor() {
            
            @Override
            public boolean execute(final Statement statement, final String sql) throws SQLException {
                return statement.execute(sql, autoGeneratedKeys);
            }
        });
    }
    
    public boolean execute(final int[] columnIndexes) throws SQLException {
        return execute(new Executor() {
            
            @Override
            public boolean execute(final Statement statement, final String sql) throws SQLException {
                return statement.execute(sql, columnIndexes);
            }
        });
    }
    
    public boolean execute(final String[] columnNames) throws SQLException {
        return execute(new Executor() {
            
            @Override
            public boolean execute(final Statement statement, final String sql) throws SQLException {
                return statement.execute(sql, columnNames);
            }
        });
    }
    
    private boolean execute(final Executor executor) throws SQLException {
        Context context = MetricsContext.start("ShardingStatement-execute");
        if (1 == statements.size()) {
            StatementEntity entity = statements.iterator().next();
            boolean result = executor.execute(entity.statement, entity.sql);
            MetricsContext.stop(context);
            return result;
        }
        List<Boolean> result = executorEngine.execute(statements, new ExecuteUnit<StatementEntity, Boolean>() {
            
            @Override
            public Boolean execute(final StatementEntity input) throws Exception {
                return executor.execute(input.statement, input.sql);
            }
        });
        MetricsContext.stop(context);
        return result.get(0);
    }
    
    private interface Updater {
        
        int executeUpdate(Statement statement, String sql) throws SQLException;
    }
    
    private interface Executor {
        
        boolean execute(Statement statement, String sql) throws SQLException;
    }
    
    @RequiredArgsConstructor
    private class StatementEntity {
        
        private final String sql;
        
        private final Statement statement;
    }
}
