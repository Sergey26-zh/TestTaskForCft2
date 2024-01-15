# Инструкции по Запуску Проекта

## Требования
- Java: версия 17
- Gradle (Kotlin): версия 8.4

## Запуск проекта
1. Клонируйте репозиторий: git clone https://github.com/Sergey26-zh/TestTaskForCft2.git
2. Перейдите в каталог проекта: `cd TestTaskForCft2`
3. Запустите приложение: 
   - Если используется Gradle: `./gradlew run`

## Зависимости
- JUnit 5: версия 5.9.1 [[Ссылка](https://example.com/library1)]
- Commons CLI: версия 1.4 [[Ссылка](https://example.com/library2)]
- Lombok: версия 1.18.26 [[Ссылка](https://example.com/library2)]
- Mockito: версия 5.8.0 [[Ссылка](https://example.com/library2)]
- Commons IO: версия 2.11.0 [[Ссылка](https://example.com/library2)]

## Использование собранного JAR-файла
   - Если вы не хотите собирать проект вручную, вы можете использовать уже собранный JAR-файл, предоставляемый в репозитории. Для этого выполните следующие шаги:
     1. Перейдите в каталог `build/libs`.
     2. Найдите файл `TestTaskForCft2.jar`.
     3. Запустите приложение командой: `java -jar TestTaskForCft2.jar
   
# Примеры использования приложения
   java -jar TestTaskForCft2.jar -s -a -p sample- in1.txt in2.txt
