# Automation QA Java Unit tests

Юнит-тесты для проверки веб-приложения [**Stellar Burgers**](https://stellarburgers.nomoreparties.site/)

### Задачи
* Покрыть тестами основные классы: Bun, Burger, Ingredient, IngredientType
* Обеспечить атомарность, независимость и неизбыточность тестов
* Использовать моки, параметризацию
* Сформировать отчёт о тестовом покрытии в Jacoco

## Стек
* Java 11
* Maven 3.8.1
* JUnit 4.13.2
* Mockito 4.8.0
* Jacoco 0.8.7

## Запуск тестов и построение отчёта
* mvn clean test
* mvn verify
