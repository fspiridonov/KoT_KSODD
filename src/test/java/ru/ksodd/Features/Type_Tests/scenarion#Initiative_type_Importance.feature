@scenario#Initiative_type_Importance

Feature: scenario#Initiative_type_Importance

  Scenario: Авторизация
    When autorization with login "test2" and password "123456"

    Scenario: Важная
      When клик на кнопку 'Новая инициатива'
      When Выбирает улицу
      When Выбирает 'Тип' 'Важная'
      When Прикрепляем файл
      When создание новой инициативы для "Улица Варварка"
      Then Проверяет, что форма закрылась

  Scenario: Выход из системы
    When logout
