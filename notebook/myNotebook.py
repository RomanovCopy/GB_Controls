import json
import os

# Функция для создания новой заметки
def create_note():
    title = input("Введите заголовок заметки: ")
    body = input("Введите текст заметки: ")
    note = {
        "id": generate_unique_id(),
        "title": title,
        "body": body,
        "timestamp": get_current_timestamp()
    }
    notes.append(note)
    save_notes()
    print("Заметка успешно создана!")

#генератор уникального Id
def generate_unique_id():
    existing_ids = set(note['id'] for note in notes)
    new_id = 1
    while new_id in existing_ids:
        new_id += 1
    return new_id

# Функция для редактирования существующей заметки
def edit_note():
    note_id = int(input("Введите Id заметки для редактирования: "))
    note=find_note_by_id(note_id)
    if note is not None:
        new_title = input(f"Текущий заголовок: {note['title']}\nВведите новый заголовок: ")
        new_body = input(f"Текущий текст заметки: {note['body']}\nВведите новый текст: ")
        note["title"] = new_title
        note["body"] = new_body
        note["timestamp"] = get_current_timestamp()
        save_notes()
        print("Заметка успешно отредактирована!")
    else:
        print("Заметка с таким Id не найдена.")

# Функция для удаления заметки
def delete_note():
    note_id = int(input("Введите Id заметки для удаления: "))
    note=find_note_by_id(note_id)
    if note is not None:
        notes.remove(note)
        save_notes()
        print(f"Заметка '{note['title']}' успешно удалена!")
    else:
        print("Заметка с таким Id не найдена.")

# Функция для вывода списка всех заметок
def list_notes():
    if not notes:
        print("Список заметок пуст.")
    else:
        # Сортировка заметок по дате (по убыванию)
        sorted_notes = sorted(notes, key=lambda x: x['timestamp'], reverse=True)
        
        for note in sorted_notes:
            print("---------")
            print(f"Id:\t{note['id']}.\nTitle:\t{note['title']}\nLast Modified Time:\t{note['timestamp']}")
            print(f"Note:\t{note['body']}")
            print()

# Функция для сохранения заметок в JSON-файл
def save_notes():
    try:
        with open("notes.json", "w", encoding="utf-8") as file:
            json.dump(notes, file, ensure_ascii=False, indent=4)
        print("Заметки успешно сохранены в файле 'notes.json'.")
    except FileNotFoundError:
        print("Ошибка: Файл 'notes.json' не найден.")
    except Exception as e:
        print(f"Произошла ошибка при сохранении заметок: {str(e)}")
# Функция для получения текущей даты и времени в строковом формате
def get_current_timestamp():
    from datetime import datetime
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")

#поиск записи по Id
def find_note_by_id(note_id):
    for note in notes:
        if note["id"] == note_id:
            return note
    return None

# Главная функция приложения
def main():
    global notes
    try:
        if os.path.exists("notes.json"):
            with open("notes.json", "r", encoding="utf-8") as file:
                notes = json.load(file)
        else:
            notes = []
    except FileNotFoundError:
        print("Файл 'notes.json' не найден.")
    except json.JSONDecodeError as e:
        print(f"Ошибка при разборе JSON: {str(e)}")
    except Exception as e:
        print(f"Произошла ошибка при загрузке заметок: {str(e)}")

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
