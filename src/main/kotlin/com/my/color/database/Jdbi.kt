package com.my.color.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.flywaydb.core.Flyway
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.KotlinPlugin
import org.jdbi.v3.sqlite3.SQLitePlugin


private lateinit var fileName: String

private val dataSource: HikariDataSource by lazy {
    val url = "jdbc:sqlite:$fileName"
    val hikariConfig = HikariConfig()
    hikariConfig.poolName = "SQLiteConnectionPool"
    hikariConfig.driverClassName = "org.sqlite.JDBC";
    hikariConfig.jdbcUrl = url
    hikariConfig.connectionTestQuery = "SELECT 1";
    hikariConfig.maxLifetime = 60000; // 60 Sec
    hikariConfig.idleTimeout = 45000; // 45 Sec
    hikariConfig.maximumPoolSize = 5; // 5 Connections (including idle connections)
    HikariDataSource(hikariConfig)
}

val db: Jdbi by lazy {
    Jdbi
            .create(dataSource)
            .installPlugin(SQLitePlugin())
            .installPlugin(KotlinPlugin())
}

fun initDb(name: String) {
    println("~~~ Initializing Db $name")
    fileName = name
    val flyway = Flyway.configure().dataSource(dataSource).load()

    val success = flyway.migrate()
    println("~~~ Migrations run $success")
}
