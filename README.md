## Программное обеспечение

Для запуска проекта необходимо установить ПО:
- IntelliJ IDEA
- Docker Desktop
- Google Chrome

## Запуск автотестов

1. Клонировать проект из репозитория https://github.com/ChQAMID/Diplom
2. Открыть проект в IntelliJ IDEA
3. Запустить приложение Docker Desktop
4. Запустить контейнер командой docker-compose up  в терминале
5. Запустить тестируемое приложение командой java -jar ./artifacts/aqa-shop.jar
6. Запустить автотесты командой ./gradlew clean test
7. Запустить отчет командой ./gradlew allureServe
8. Выйти из приложения сочетанием клавиш Ctrl+С
9. Завершить работу контейнера командой docker-compose down