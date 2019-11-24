package com.my.color.database

import spock.lang.Specification

class ColorRepositoryTest extends Specification {

    def setupSpec() {
        JdbiKt.initDb("test_db")
    }

    def cleanupSpec() {
        JdbiKt.dataSource.close()
        new File("test_db").delete()
    }

    def "Read all colors"() {
        expect:
        def colors = ColorRepository.INSTANCE.findAll()
        colors.size() == 2
    }
}

