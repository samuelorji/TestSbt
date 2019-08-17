package de.bonify.project.core

import de.bonify.project.TestServiceT
import de.bonify.project.core.config.BonifyConfig

class BonifyConfigSpec extends TestServiceT {
  BonifyConfig.postgresDbHost should be("localhost")
  BonifyConfig.postgresDbName should be ("postgres")
  BonifyConfig.postgresDbPass should be("postgres")
  BonifyConfig.postgresDbPort should be (5432)
  BonifyConfig.postgresDbName should be ("postgres")
  BonifyConfig.webHost should be("127.0.0.1")
  BonifyConfig.webPort should be (8082)
}
