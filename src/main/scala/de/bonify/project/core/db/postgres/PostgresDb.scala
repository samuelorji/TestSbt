package de.bonify.project
package core.db.postgres

import core.config.BonifyConfig

import com.github.mauricio.async.db.Configuration
import com.github.mauricio.async.db.pool.{ ConnectionPool, PoolConfiguration }
import com.github.mauricio.async.db.postgresql.pool.PostgreSQLConnectionFactory

/**
  * Database configurations*/
private[postgres] object PostgresDb {
  private val configuration = new Configuration(
    username = BonifyConfig.postgresDbUser,
    host     = BonifyConfig.postgresDbHost,
    port     = BonifyConfig.postgresDbPort,
    password = Some(BonifyConfig.postgresDbPass),
    database = Some(BonifyConfig.postgresDbName)

  )

  private val poolConfiguration = new PoolConfiguration(
    maxObjects   = BonifyConfig.postgresDbPoolMaxObjects,
    maxIdle      = BonifyConfig.postgresDbPoolMaxIdle,
    maxQueueSize = BonifyConfig.postgresDbPoolMaxQueueSize
  )

  private val factory = new PostgreSQLConnectionFactory(configuration)
  lazy val pool       = new ConnectionPool(factory,poolConfiguration)

}

trait PostgresDb{
  lazy val pool = PostgresDb.pool
}
