# Приложение для конвертации валют (Currency Converter)
Тестовое Задание для участия в стажировке компании Вконтакте. 

## Описание
Приложение позволяет пользователям конвертировать сумму из одной валюты в другую, используя актуальные курсы обмена.

## Особенности Тестового Задания
Экран ввода:
- Поле ввода для суммы, которую нужно конвертировать.
- Выпадающие списки для выбора валюты (например, USD, EUR, GBP).
- Кнопка для начала конвертации.

Экран результата:
- Отображение сконвертированной суммы в выбранной валюте.

Получение данных:
- Для получения обменных курсов используется публичное API от exchangerate-api (https://www.exchangerate-api.com/).

## Использованные Технологии
- Kotlin
- Jetpack Compose
- Jetpack Navigation
- Dagger 2
- Retrofit 2
- Clean Architecture
- MVVM
- Kotlin Coroutines и Flow

## Инструкция по сборке проекта
В файл gradle.properties необходимо добавить ваш apikey в следующем формате:
apikey=YOUR_API_KEY

## Скриншоты
<p align="center">
  <table align="center" cellspacing="4">
    <tr>
      <td width="25%">Главный экран</td>
      <td width="25%">Экран с детальной информацией</td>
      <td width="25%">Экран с ошибкой</td>
      <td width="25%">Экран с прогресс баром</td>
    </tr>
    <tr>
      <td><img src="https://github.com/user-attachments/assets/809b00b3-c2a1-4cf7-b0b7-c1dcf1dcb037"/></td>
      <td><img src="https://github.com/user-attachments/assets/ed3a095d-2860-495c-bf14-7073be1db8f8"/></td>
      <td><img src="https://github.com/user-attachments/assets/5133be5e-3bc7-4117-ac26-a3f01d7382d5"/></td>
      <td><img src="https://github.com/user-attachments/assets/b7bf2803-a620-4440-a8c8-ed5a0933a74b"/></td>
    </tr>
  </table>
</p>
