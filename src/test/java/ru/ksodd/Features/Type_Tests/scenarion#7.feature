@scenario#7

  Feature: scenarion#7

    Scenario: Авторизация
      When вводим логин "test2"
      When вводим пароль "123456"
      When клик на кнопку 'Войти'

    Scenario: Проект МВК
      When войти в Проекты МВК
      Then заполнят поле Организация
      When заполнят поле Содержание работ
      When заполнить поле Дата реализации
      When загрузить файлы Проекты МВК
      When клик на кнопку 'Создать'

      When клик на кнопку 'Добавить'

    Scenario: ▲▲▲▲▲▲▲ Последний шаг ▲▲▲▲▲▲▲
      When выход из системы

