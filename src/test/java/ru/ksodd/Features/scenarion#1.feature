@scenario#1

Feature: scenario#1

  Scenario: Авторизация
    When вводим логин "test2"
    When вводим пароль "123456"
    When клик на кнопку 'Войти'

  Scenario: create new requests based on initiative
    When Клик на кнопку 'Новая инициатива'
    When Выбирает улицу
    When Выбирает 'Тип' 'Важная'
    When Прикрепляем файл
    When Создание новой инициативы для "Улицы Мясницкая"
    Then Проверяет, что форма закрылась

  Scenario: Выход из системы
    When выход из системы