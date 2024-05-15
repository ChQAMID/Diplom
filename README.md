# Перечень автоматизируемых сценариев

### Валидные данные зарегистрированной карты:
- *Номер карты* – 4444 4444 4444 4441
- *Месяц* -  цифры в формате "ММ" в диапазоне 01-12. В текущем году номер месяца до текущего включительно.
- *Год* - цифры в формате "YY" не ранее текущего года
- *Владелец* - буквы на латинице в количестве от 2 до 64 включительно.
- *CVC/CVV* - 3 цифры в диапазоне 001-999

### Валидные данные отклоненной карты:
- *Номер карты* – 4444 4444 4444 4442
- *Месяц* -  цифры в формате "ММ" в диапазоне 01-12. В текущем году номер месяца до текущего включительно.
- *Год* - цифры в формате "YY" не ранее текущего года
- *Владелец* - буквы на латинице в количестве от 2 до 64 включительно.
- *CVC/CVV* - 3 цифры в диапазоне 001-999

## Тест-кейсы:
### 1.	Оплата зарегистрированной картой с валидными данными
Заполнить форму валидными данными

*Ожидаемый результат:* Появляется сообщение "Успешно! Операция одобрена Банком"

### 2.	Выдача кредита по зарегистрированной карте с валидными данными
Заполнить форму валидными данными

*Ожидаемый результат:* Появляется сообщение "Успешно! Операция одобрена Банком"

### 3.	Оплата отклоненной картой с валидными данными
Заполнить форму валидными данными

*Ожидаемый результат:* Появляется сообщение "Ошибка! Банк отказал в проведении операции"

### 4.	Выдача кредита по отклоненной карте с валидными данными
Заполнить форму валидными данными

*Ожидаемый результат:* Появляется сообщение "Ошибка! Банк отказал в проведении операции"

### 5.	Отправка пустой формы
Отправить форму с незаполненными полями

*Ожидаемый результат:* Форма не отправляется, под полями появляется сообщение обратной связи "Неверный формат"

### 6.	Отправка формы с невалидным номером карты
Заполнить форму валидными данными, в поле "Номер карты" ввести случайные 16 цифр

*Ожидаемый результат:* Появляется сообщение "Ошибка! Банк отказал в проведении операции"

### 7.	Отправка формы с неполным номером карты
Заполнить форму валидными данными, в поле "Номер карты" ввести неполный номер одобренной карты, например, 4444 4444 4444 444

*Ожидаемый результат:* Форма не отправляется, под полем "Номер карты" появляется сообщение обратной связи "Неверный формат"

### 8.	Ввод 17 цифр в поле номера карты
Заполнить форму валидными данными, в поле номер карты ввести 17 цифр

*Ожидаемый результат:* Поле заполняется 16 цифрами

### 9.	Отправка формы с месяцем 00
Заполнить форму валидными данными, в поле "Месяц" ввести "00"

*Ожидаемый результат:* Форма не отправляется, под полем "Месяц" появляется сообщение обратной связи "Неверный формат"

### 10.	Отправка формы с месяцем 13
Заполнить форму валидными данными, в поле "Месяц" ввести "13"

*Ожидаемый результат:* Форма не отправляется, под полем "Месяц" появляется сообщение обратной связи "Неверно указан срок действия карты"

### 11.	Отправка формы с месяцем до текущего (текущего года)
Заполнить форму валидными данными, в поле "Месяц" ввести месяц до текущего, например, 01, в поле "Год" ввести текущий год

*Ожидаемый результат:* Форма не отправляется, под полем "Месяц" появляется сообщение обратной связи "Неверно указан срок действия карты"

### 12.	Ввод одной цифры в поле "месяц"
Заполнить форму валидными данными, в поле "Месяц" ввести одну цифру

*Ожидаемый результат:* Форма не отправляется, под полем "Месяц" появляется сообщение обратной связи "Неверный формат"

### 13.	Ввод трех цифр в поле "месяц"
Заполнить форму валидными данными, в поле "Месяц" ввести три цифры

*Ожидаемый результат:* Поле "Месяц" заполняется двумя цифрами

### 14.	Ввод букв в поле "месяц"
Заполнить форму валидными данными, в поле "Месяц" ввести любые 2 буквы

*Ожидаемый результат:* Поле не принимает значение

### 15.	Ввод спецсимволов в поле "месяц"
Заполнить форму валидными данными, в поле "Месяц" ввести 2 спецсимвола

*Ожидаемый результат:* Поле не принимает значение

### 16.	Отправка формы с годом из прошлого
Заполнить форму валидными данными, в поле "Год" ввести год из прошлого

*Ожидаемый результат:* Форма не отправляется, под полем "Год" появляется сообщение обратной связи "Истёк срок действия карты"

### 17.	Отправка формы с годом, после окончания срока действия карты
Заполнить форму валидными данными, в поле "Год" ввести год после окончания срока действия карты, например, 30

*Ожидаемый результат:* Форма не отправляется, под полем "Год" появляется сообщение обратной связи "Неверно указан срок действия карты"

### 18.	Ввод одной цифры в поле "год"
Заполнить форму валидными данными, в поле "Год" ввести одну цифру

*Ожидаемый результат:* Форма не отправляется, под полем "Год" появляется сообщение обратной связи "Неверный формат"

### 19.	Ввод трех цифр в поле "год"
Заполнить форму валидными данными, в поле "Год" ввести три цифры

*Ожидаемый результат:* Поле заполняется двумя цифрами

### 20.	Ввод букв в поле "год"
Заполнить форму валидными данными, в поле "Год" ввести любые 2 буквы

*Ожидаемый результат:* Поле не принимает значение

### 21.	Ввод спецсимволов в поле "год"
Заполнить форму валидными данными, в поле "Год" ввести любые 2 спецсимвола

*Ожидаемый результат:* Поле не принимает значение

### 22.	Ввод кириллицы в поле "Владелец"
Заполнить форму валидными данными, в поле "Владелец" ввести фамилию и имя на кириллице

*Ожидаемый результат:* Поле не принимает значение

### 23.	Ввод цифр в поле "Владелец"
Заполнить форму валидными данными, в поле "Владелец" ввести цифры

*Ожидаемый результат:* Поле не принимает значение

### 24.	Ввод спецсимволов в поле "Владелец"
Заполнить форму валидными данными, в поле "Владелец" ввести спецсимволы

*Ожидаемый результат:* Поле не принимает значение

### 25.	Ввод в поле "Владелец" 85 букв на латинице
Заполнить форму валидными данными, в поле "Владелец" ввести 85 букв на латинице

*Ожидаемый результат:* Под полем появляется сообщение обратной связи об ограничении количества букв.

### 26.	Ввод двух цифр в поле "CVC/CVV"
Заполнить форму валидными данными, в поле "CVC/CVV" ввести 2 цифры

*Ожидаемый результат:* Форма не отправляется, под полем "CVC/CVV" появляется сообщение обратной связи "Неверный формат"

### 27.	Ввод четырех цифр в поле "CVC/CVV"
Заполнить форму валидными данными, в поле "CVC/CVV" ввести 4 цифры

*Ожидаемый результат:* Поле заполняется тремя цифрами

### 28.	Ввод букв в поле "CVC/CVV"
Заполнить форму валидными данными, в поле "CVC/CVV" ввести 3 буквы

*Ожидаемый результат:* Поле не принимает значение

### 29.	Ввод спецсимволов в поле "CVC/CVV"
Заполнить форму валидными данными, в поле "CVC/CVV" ввести 3 спецсимвола

*Ожидаемый результат:* Поле не принимает значение

### 30.	Проверка в СУБД оплаты по зарегистрированной карте
Заполнить форму валидными данными

*Ожидаемый результат:* Сумма оплаты 45 000

### 31.	Проверка в СУБД оплаты по отклоненной карте
Заполнить форму валидными данными

*Ожидаемый результат:* Сумма оплаты 0


## Перечень используемых инструментов
Java - язык программирования
IntelliJ IDEA – редактор кода
Gradle - система автоматической сборки
JUnit5 - библиотека для тестирования программного обеспечения
Selenide - инструмент для тестирования Web-приложений
Lombok - библиотека генерации кода
Faker - библиотека генерации данных
Docker - система контейнеризации
Git - система контроля версий
GitHub – онлайн-репозиторий
Allure - инструмент построения отчетов автотестов
MySQL, Postgres – системы управления базами данных

## Перечень и описание возможных рисков при автоматизации

- Дополнительная стоимость работ
- Недостаточный уровень навыков программирования
- Технические сложности (доступ к БД, работоспособность приложения, связь)

## Интервальная оценка с учётом рисков в часах

- Написание классов и автотестов – 56 часов
- Составление баг-репортов и отчета – 16 часов

## План сдачи работ: когда будут готовы автотесты, результаты их прогона

- Написание классов и автотестов  - 19.05.24
- Составление баг-репортов и отчета – 22.05.24