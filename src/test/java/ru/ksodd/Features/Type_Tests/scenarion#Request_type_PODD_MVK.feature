@scenario#Request_type_PODD_MVK

  Feature: scenarion#Request_type_PODD_MVK
    Scenario: Authorization
      When autorization with login "test2" and password "123456"

    Scenario: 'ПОДДы МВК'
      When перенос инициативы в запрос
      Then проверка открытия выпадающего списка в поле 'Улица'
      When выбор улицы из выпадающего списка
      When выбор типа 'ПОДДы МВК'
      When заполняет поле 'Протяженность, м'
      When заполняет поле 'Содержание работ'
      Then проверяет, что появились характерные Подд чек-боксы
      When заполняет поле 'Причина' 'при создании запроса, с улицей на которую уже есть запросы'
      When клик на кнопку 'Создать'

#      When входит в чек-бокс 'Принято в работу'
#      When кликает на кнопку 'Согласовано'
#      When входит в чек-бокс 'Натурные обследования'
#      When кликает на кнопку 'Не согласовано'
#      When проверяет, что появилась в поле причины отказа появилась подсказка 'Необходимо ввести комментарий!'
#      When вводит в поле причины отказа значение
#      When кликает на кнопку 'Не согласовано'
#      When входит в отклонённый чек-бокс 'Натурные обследования'
#      When кликает на кнопку 'Передал/Отправил'
#      When входит в чек-бокс 'Натурные обследования'
#      When кликает на кнопку 'Согласовано'

#    Then проверка загрузки файлов 'их наличие внутри запроса'
#      When входит в чек-бокс 'Проектирование'
#      When загружает файлы
#      When кликает на кнопку 'Передал/Отправил'
#      When входит внутрь тестового запроса
#      When проверяет наличие файлов
#      When кликает на кнопку 'Отмена'

#    Scenario: Пятый
#      When перенос тестового запроса в этап пакеты
#
#      When входит в чек-бокс 'Авторский надзор'
#      When кликает на кнопку 'Не согласовано'
#      When проверяет, что появилась в поле причины отказа появилась подсказка 'Необходимо ввести комментарий!'
#      When вводит в поле причины отказа значение
#      When кликает на кнопку 'Не согласовано'
#      When входит в отклонённый чек-бокс 'Авторский надзор'
#      When проверят комментарий введённый при отклонении
#      When кликает на кнопку 'Повторно отправил'
#      When входит в чек-бокс 'Авторский надзор'
#      When кликает на кнопку 'Согласовано'
#
#      When входит в чек-бокс 'Дирекция ОДД'
#      When кликает на кнопку 'Не согласовано'
#      When проверяет, что появилась в поле причины отказа появилась подсказка 'Необходимо ввести комментарий!'
#      When вводит в поле причины отказа значение
#      When кликает на кнопку 'Не согласовано'
#      When входит в отклонённый чек-бокс 'Дирекция ОДД'
#      When проверят комментарий введённый при отклонении
#      When кликает на кнопку 'Повторно отправил'
#      When входит в чек-бокс 'Дирекция ОДД'
#      When кликает на кнопку 'Согласовано'

    Scenario: Выход из системы
      When logout