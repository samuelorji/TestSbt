package de.bonify.project
package core.config

import scala.concurrent.duration.FiniteDuration
import scala.util.Try

import com.typesafe.config.ConfigFactory

/**
  * Contains configurations shared across the project*/
object BonifyConfig {
  val config = ConfigFactory.load()

  // Web Interface
  val webHost = config.getString("bonify.interface.web.host")
  val webPort = config.getInt("bonify.interface.web.port")

  // postgres

  val postgresDbHost  = config.getString("bonify.db.postgres.host")
  val postgresDbPort  = config.getInt("bonify.db.postgres.port")
  val postgresDbUser  = config.getString("bonify.db.postgres.user")
  val postgresDbPass  = config.getString("bonify.db.postgres.pass")
  val postgresDbName  = config.getString("bonify.db.postgres.name")

  val postgresDbPoolMaxObjects   = config.getInt("bonify.db.postgres.pool.max-objects")
  val postgresDbPoolMaxIdle      = config.getInt("bonify.db.postgres.pool.max-idle")
  val postgresDbPoolMaxQueueSize = config.getInt("bonify.db.postgres.pool.max-queue-size")

  //timeouts
  val httpRequestsTimeout   = Try(FiniteDuration(config.getInt("bonify.web.http-requests-timeout"),"seconds")).toOption.get


}
