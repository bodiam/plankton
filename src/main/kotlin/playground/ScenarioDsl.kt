package playground

data class ScenarioBuilder(var name: String?) {
    fun post(url: String, block: HttpBuilder.() -> Unit) {
        println("POST")
    }

    fun delete(url: String, block: HttpBuilder.() -> Unit) {
        println("DELETE")
    }

    fun get(url: String, block: HttpBuilder.() -> Unit) {
        println("GET")
    }

}

class HttpBuilder {
    fun headers(block: HeaderBuilder.() -> Unit) {

    }

    fun json(block: String) {

    }

    fun expect()
}


class HeaderBuilder {
    fun header(name: String, value: String): Unit = TODO()
}

data class ScenarioResult(var scenario: ScenarioBuilder)

class SystemOutReporter {
    fun report(result: ScenarioResult) {
        println("reporting")
    }
}

fun scenario(name: String, block: ScenarioBuilder.() -> Unit): ScenarioResult {
    val scenario = ScenarioBuilder(name)
    scenario.block()
    return ScenarioResult(scenario)
}


//////////////////////////////////////////////////////////////////

const val token = "123"
const val payload = """
            {
                "test":"somevalue",
                "otherValue": "bla"
            }
            """

val result = scenario("Send mailpack") {
    post("/mailpack") {
        headers {
            header("Authorization", token)
        }
        json(payload)
        expect {
            that(http.status).isEqualTo(200)
        }
    }


}

val ignoreMe = SystemOutReporter().report(result)

