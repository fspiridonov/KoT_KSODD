
@scenario#CreateMVK

Feature: scenario#1

  Scenario: Авторизация
    When вводим логин "test2"
    When вводим пароль "123456"
    When клик на кнопку 'Войти'

    Scenario: MVK

#      When клик на MVK
      When войти в Проекты МВК
      When ввод улицы в поле Улица
      When ввод в поле Организация
      When ввод в поле Содержание работ
      When выбор даты
      When ввод в поле Протокол
      When загрузить файлы Проекты МВК
      When клик на кнопку 'Создать'
