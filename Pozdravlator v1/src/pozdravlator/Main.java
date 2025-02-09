package pozdravlator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		ArrayListCollection arrayBirthday = new ArrayListCollection();
		Scanner scanner = new Scanner(System.in);
		
		arrayBirthday.ReadFromFile();
		
		System.out.println(" ");

		String menuElem[] = {
				
				"1. Добавить новую запись.",
				"2. Удалить запись.",
				"3. Редактировать запись.",
				"4. Вывести список всех дней рождений.",
				"5. Загрузить список из файла.",
				"6. Сохранить список в файле.",
				"7. Выход."
				
		};
			
		while(true) {
			
			arrayBirthday.upcomingBirthday();
			
			for (int i = 0; i < menuElem.length; i++) {
						
				System.out.println(menuElem[i]);
						
			}
			
			System.out.print("Введите номер пункта меню: ");
			int activeElemMenu = scanner.nextInt();
			scanner.nextLine();
			
			switch(activeElemMenu) {
			
				case 1: arrayBirthday.addElem(scanner);
				break;
				case 2: arrayBirthday.deleteElem(scanner);
				break;
				case 3: arrayBirthday.redactElem(scanner);
				break;
				case 4: arrayBirthday.printArrayBirthday();
				break;
				case 5: arrayBirthday.ReadFromFile();
				break;
				case 6: arrayBirthday.WriteInFileList();
				break;
				case 7: System.exit(0);
				break;
				default: System.err.println("Некорректный пункт меню!");
			
			}
			
			System.out.println(" ");
			
		}
		
	}

}
