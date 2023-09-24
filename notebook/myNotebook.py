import json
import os

# Функция для создания новой заметки
def create_note():
    title = input("Введите заголовок заметки: ")
    body = input("Введите текст заметки: ")
    note = {
        "id": len(notes) + 1,
        "title": title,
        "body": body,
        "timestamp": get_current_timestamp()
    }
    notes.append(note)
    save_notes()
    print("Заметка успешно создана!")

# Функция для редактирования существующей заметки
def edit_note():
    note_id = int(input("Введите номер заметки для редактирования: "))
    if note_id <= len(notes):
        note = notes[note_id - 1]
        new_title = input(f"Текущий заголовок: {note['title']}\nВведите новый заголовок: ")
        new_body = input(f"Текущий текст заметки: {note['body']}\nВведите новый текст: ")
        note["title"] = new_title
        note["body"] = new_body
        note["timestamp"] = get_current_timestamp()
        save_notes()
        print("Заметка успешно отредактирована!")
    else:
        print("Заметка с таким номером не найдена.")

# Функция для удаления заметки
def delete_note():
    note_id = int(input("Введите номер заметки для удаления: "))
    if note_id <= len(notes):
        deleted_note = notes.pop(note_id - 1)
        save_notes()
        print(f"Заметка '{deleted_note['title']}' успешно удалена!")
    else:
        print("Заметка с таким номером не найдена.")

# Функция для вывода списка всех заметок
def list_notes():
    if not notes:
        print("Список заметок пуст.")
    else:
        for note in notes:
            print(f"{note['id']}. {note['title']} (Дата/время: {note['timestamp']})")
            print(note['body'])
            print()

# Функция для сохранения заметок в JSON-файл
def save_notes():
    with open("notes.json", "w") as file:
        for note in notes:
            json.dump(note, file, indent=4)
            file.write(";")
# Функция для получения текущей даты и времени в строковом формате
def get_current_timestamp():
    from datetime import datetime
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

# Главная функция приложения
def main():
    global notes
    if os.path.exists("notes.json"):
        with open("notes.json", "r") as file:
            notes = json.load(file)
    else:
        notes = []

    while True:
        print("\nМеню:")
        print("1. Создать заметку")
        print("2. Редактировать заметку")
        print("3. Удалить заметку")
        print("4. Показать список заметок")
        print("5. Выйти из приложения")

        choice = input("Выберите действие (1/2/3/4/5): ")

        if choice == "1":
            create_note()
        elif choice == "2":
            edit_note()
        elif choice == "3":
            delete_note()
        elif choice == "4":
            list_notes()
        elif choice == "5":
            print("Выход из приложения.")
            break
        else:
            print("Неверный выбор. Попробуйте еще раз.")

if __name__ == "__main__":
    main()

