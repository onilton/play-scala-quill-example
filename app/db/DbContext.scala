package db

import javax.sql.DataSource
import play.api.db.Database;
import javax.inject.{ Inject, Singleton }
import io.getquill.H2JdbcContext
import io.getquill.SnakeCase
import java.io.Closeable

@Singleton
class DbContext @Inject()(db: Database) {
  /* If you want to change the underlying database, change to one of the contexts available at
   * https://getquill.io/#contexts-quill-jdbc
   * i.e. H2JdbcContext, PostgresJdbcContext, MysqlJdbcContext, SqliteJdbcContext,
   * SqlServerJdbcContext or OracleJdbcContext
   */
  val ctx = new H2JdbcContext(SnakeCase, db.dataSource.asInstanceOf[DataSource with Closeable])
}
