@scenario5#AvariyProject

Feature: scenario#999

  Scenario: Авторизация
    When вводим логин "test2"
    When вводим пароль "123456"
    When клик на кнопку 'Войти'

  Scenario: 111

    When войти в Проекты МВК
    When Клик на кнопку 'Аварийный проект'


    When ввод улицы в поле Улицы
#      When Заполнения  значении "" в поле ""
    When Заполнение значении "" в поле "Содержание работ"
    When Заполнение значении "" в поле "Организация"
    When Выбор типа в поле Тип согласования
    When загрузить файлы Проекты МВК
    When Клик на кнопку 'Согласовано' на форме

