package de.bonify.project
package core.db.postgres

import Server.BankRecord

/**
  * Object used for database Queries*/
object BonifyMapper extends PostgresDb {

  def insertIntoDatabase(records : List[BankRecord]) = {
    val query = s"INSERT INTO bank_record (bank_name,bank_identifier) VALUES " + records.foldLeft(("",1)){
      case ((str,ind), record) =>
        if(ind < records.length) {
          (str + s"('${record.bank }','${record.identifier}'),",ind+1)
        }else{
          (str + s"('${record.bank }','${record.identifier}');",ind+1)
        }
    }._1

    pool.sendPreparedStatement(query)
  }

  def findById(identifier : String) = {
    val queryString = s"select * from bank_record where bank_identifier='$identifier'"
    pool.sendPreparedStatement(queryString)
  }

}