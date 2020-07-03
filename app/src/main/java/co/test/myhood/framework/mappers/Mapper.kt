package co.test.myhood.framework.mappers

abstract class Mapper<E, T> {
    abstract fun map(input: E): T
}