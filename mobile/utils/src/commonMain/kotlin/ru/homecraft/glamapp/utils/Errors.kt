package ru.homecraft.glamapp.utils

interface AppError

interface UIError : AppError

interface PresentationError : AppError

interface DomainError : AppError {
    data object DummyDomainError: DomainError
}

interface DataError : AppError {
    interface LocalDataError : DataError {
        data class DatabaseWrightError(val cause: String): LocalDataError
    }
}

data class MappingError(val message: String) : AppError