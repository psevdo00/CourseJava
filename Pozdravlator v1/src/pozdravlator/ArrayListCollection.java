package pozdravlator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;

public class ArrayListCollection {

	private ArrayList<Birthday> birthdays;
	
	public ArrayListCollection() {
		
		birthdays = new ArrayList<Birthday>();
		
	}
	
	public void addElem(Scanner scanner) {
		
		birthdays.add(birthdayInfo(scanner));
		
	}
	
	public Birthday birthdayInfo(Scanner scanner) {
		
		System.out.print("\nВведите дату рождения (через пробел): ");
		
		int day = scanner.nextInt();
		int month = scanner.nextInt();
		int year = scanner.nextInt();
		scanner.nextLine();
		
		System.out.print("Введите ФИО человека: ");
		
		String fullName = scanner.nextLine();
		
		Birthday birthday = new Birthday(day, month, year, fullName);
		
		return birthday;
		
	}
	
	public void deleteElem(Scanner scanner) {
		
		printArrayBirthday();
		
		System.out.print("\nВведите номер записи, которую хотите удалить: ");
		int deleteIndexElem = scanner.nextInt() - 1;
		
		if (deleteIndexElem < 0 || deleteIndexElem >= birthdays.size()) {
			
			System.err.println("Неверный номер записи!");
			
		} else {
			
			birthdays.remove(deleteIndexElem);
			System.out.println("Запись удалена!");
			
		}

	}
	
	public void redactElem(Scanner scanner) {
		
		printArrayBirthday();
		
		System.out.print("\nВведите номер записи, которую хотите изменить: ");
		int redactIndexElem = scanner.nextInt() - 1;
		
		if (redactIndexElem < 0 || redactIndexElem >= birthdays.size()) {
			
			System.err.println("Неверный номер записи!");
			
		} else {
			
			birthdays.set(redactIndexElem, birthdayInfo(scanner));
			System.out.println("Запись изменена!");
			
		}
		
	}
	
	public void printArrayBirthday() {
		
		System.out.println("\n-----------");
		
		if (birthdays.size() < 1) {
			
			System.out.println("Список пуст!");
			
		} else {
			
			for (int i = 0; i < birthdays.size(); i++) {
			
				Birthday birthday = birthdays.get(i);
				
				System.out.println((i + 1) + ". " +birthday.fullInfoBirthday());
				
			}
			
		}

		System.out.println("-----------");
		
	}
	
	public void ReadFromFile() {
		
		try(BufferedReader reader = new BufferedReader(new FileReader("saveFiles/file.txt"))){
			
			String line;
			
			while ((line = reader.readLine()) != null) {
				
				String[] parts = line.split(" ");
				
				if(parts.length == 6) {
					
					int day = Integer.parseInt(parts[0]);
					int month = Integer.parseInt(parts[1]);
					int year = Integer.parseInt(parts[2]);
					String fullName = parts[3] + " " + parts[4] + " " + parts[5];
					
					birthdays.add(new Birthday(day, month, year, fullName));
					
				}
				
			}
			
			System.out.println("\nДанные успешно загружены!");
			
		} catch (IOException e) {
			
			System.err.println("Ошибка чтения файла " + e.getMessage());
			
		}
		
	}
	
	public void WriteInFileList() {
		
		try (BufferedWriter writter = new BufferedWriter(new FileWriter("saveFiles/file.txt"))) {
			
			for (Birthday birthday : birthdays) {
				
				writter.write(birthday.fullInfoBirthdayInFile() + "\n");
				
			}
			
			System.out.println("\nДанные успешно сохранены!");
			
		} catch (IOException e) {
			
			System.err.println("Ошибка записи в файл " + e.getMessage());
			
		}
		
		
		
	}
	
	public void upcomingBirthday() {
		
		LocalDate dateNow = LocalDate.now(); // 2025-02-06
		
		int dayNow = dateNow.getDayOfMonth();
		int monthNow = dateNow.getMonthValue();
		int count = 0;
		
		System.out.println("Ближайшие дни рождения:\n-----------");
		
		Collections.sort(birthdays, new BirthdayComparator());
		
		for (Birthday birthday : birthdays) {
			
			if (((birthday.getMonth() == monthNow && birthday.getDay() >= dayNow) || (birthday.getMonth() > monthNow)) && count < 6) {
				
				System.out.println(birthday.fullInfoBirthday());
				count++;
				
			}
			
		}
		
		System.out.println("-----------\n");;
		
	}
	
}
