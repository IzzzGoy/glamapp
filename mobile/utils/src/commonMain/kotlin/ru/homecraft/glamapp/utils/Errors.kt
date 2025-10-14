package ru.homecraft.glamapp.utils

interface AppError

interface UIError : AppError

interface PresentationError : AppError

interface DomainError : AppError {
    data object DummyDomainError: DomainError
}

interface RepositoryError : AppError