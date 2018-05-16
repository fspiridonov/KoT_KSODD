@scenario#Request_type_actual_initCODD

  Feature: scenarion#Request_type_actual_initCODD
    Scenario: Authorization
      When autorization with login "test2" and password "123456"

    Scenario: Актуализация (инициатива ЦОДД)
      When перенос инициативы в запрос
      Then проверка открытия выпадающего списка в поле 'Улица'
      When выбор улицы из выпадающего списка
      When выбор типа
      When заполняет поле 'Протяженность, м'
      When заполняет поле 'Содержание работ'
      When заполняет поле 'Причина' 'при создании запроса, с улицей на которую уже есть запросы'
      When клик на кнопку 'Создать'


    Scenario: Выход из системы
      When logout