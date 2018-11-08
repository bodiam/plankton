package playground

data class Person(var name: String? = null,
                  var age: Int? = null,
                  var address: Address? = null)


data class Address(var street: String? = null,
                   var number: Int? = null,
                   var city: String? = null)



fun personOld(block: Person.() -> Unit): Person {
    val p = Person()
//    block(p)
    p.block()
    return p
}

fun person(block: Person.() -> Unit): Person = Person().apply(block)

val person = person {
    name = "John"
    age = 25

    address {
        street = "my street"
    }
}

fun Person.address(block: Address.() -> Unit) {
    address = Address().apply(block)
}
