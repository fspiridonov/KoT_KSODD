@scenario4#mvk4

Feature: scenario#4
  Scenario: dnd
    When Перенос карточки c этапа "2" на этап "3"

  Scenario: Checkbox1
    When Клик на чек-бокс "Завершение работ на проезжей части"
    When Ввод значении "Моя прелесть!" в поле "Комментарий" в внутри формы задач
    When Клик на кнопку 'Согласовано' на форме

    When Клик на чек-бокс "Восстановление схемы (ЦОДД)"
    When Ввод значении "I'm back, baby" в поле "Комментарий" в внутри формы задач
    When Клик на кнопку 'Согласовано' на форме

    When Клик на чек-бокс "Восстановление схемы (ДТ)"
    When Ввод значении "Хьюстон, у нас проблема!" в поле "Комментарий" в внутри формы задач
    When Клик на кнопку 'Согласовано' на форме

  Scenario: Выход из системы
    When выход из системы