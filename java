package javvvaa;

import java.util.ArrayList;
import java.util.Scanner;

abstract class Media {
    String id;
    String title;
    protected int yearPublished;
    private double rentalFee;

    public Media(String id, String title, int yearPublished) {
        this.id = id;
        this.title = title;
        this.yearPublished = yearPublished;
        this.rentalFee = 1.50;
    }

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public double getRentalFee() {
        return rentalFee;
    }

    public abstract void display();

    public double calculateRentalFee() {
		return rentalFee;
        // No calculation needed for generic media
    }
}

class EBook extends Media {
    private int numChapters;

    public EBook(String id, String title, int yearPublished, int numChapters) {
        super(id, title, yearPublished);
        this.numChapters = numChapters;
    }

    @Override
    public void display() {
        System.out.println("Media ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Year Published: " + getYearPublished());
        System.out.println("Number of Chapters: " + numChapters);
        System.out.println("Rental Fee: $" + getRentalFee());
    }

    @Override
    public double calculateRentalFee() {
        double rentalFee = numChapters * 0.10 + getRentalFee();
        if (getYearPublished() > 2015) {
            rentalFee += 1.00;
        }
        setRentalFee(rentalFee);
		return rentalFee;
    }

	private void setRentalFee(double rentalFee) {
		// TODO Auto-generated method stub
		
	}
}

class MusicCD extends Media {
    private int lengthMinutes;

    public MusicCD(String id, String title, int yearPublished, int lengthMinutes) {
        super(id, title, yearPublished);
        this.lengthMinutes = lengthMinutes;
    }

    @Override
    public void display() {
        System.out.println("Media ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Year Published: " + getYearPublished());
        System.out.println("Length (minutes): " + lengthMinutes);
        System.out.println("Rental Fee: $" + getRentalFee());
    }

    @Override
    public double calculateRentalFee() {
        double rentalFee = lengthMinutes * 0.045 + getRentalFee();
        if (getYearPublished() > 2014) {
            rentalFee += 2.00;
        }
        setRentalFee(rentalFee);
		return rentalFee;
    }

	private void setRentalFee(double rentalFee) {
		// TODO Auto-generated method stub
		
	}
}

class MovieDVD extends Media {
    private int sizeMB;

    public MovieDVD(String id, String title, int yearPublished, int sizeMB) {
        super(id, title, yearPublished);
        this.sizeMB = sizeMB;
    }

    public double calculateRentalFee() {
        double rentalFee = 3.25;
        if (yearPublished > 2019) {
            rentalFee = 5.00;
        }
        return rentalFee;
    }

    public String toString() {
        return super.toString() + ", Size (MB): " + sizeMB + ", Rental Fee: $" + calculateRentalFee();
    }

	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}
}

class Manager {
    private ArrayList<Media> mediaLibrary = new ArrayList<>();

    public void loadMediaData() {
        // Load media data from data file
        // Code to load media data from file
    }

    public void displayMediaLibrary() {
        System.out.println("Media Library:");
        for (Media media : mediaLibrary) {
            System.out.println(media);
        }
    }

    public void addMedia(Media media) {
        mediaLibrary.add(media);
        System.out.println("Media added: " + media);
    }

    public Media findMedia(String mediaId) {
        for (Media media : mediaLibrary) {
            if (media.id.equals(mediaId)) {
                return media;
            }
        }
        return null;
    }

    public void removeMedia(String mediaId) {
        Media mediaToRemove = findMedia(mediaId);
        if (mediaToRemove != null) {
            mediaLibrary.remove(mediaToRemove);
            System.out.println("Media removed: " + mediaToRemove);
        } else {
            System.out.println("Media with ID " + mediaId + " not found.");
        }
    }

    public void rentMedia(String mediaId) {
        Media mediaToRent = findMedia(mediaId);
        if (mediaToRent != null) {
            double rentalFee = mediaToRent.calculateRentalFee();
            System.out.println("Rental Fee for " + mediaToRent.title + ": $" + rentalFee);
            // Code to handle renting of media
        } else {
            System.out.println("Media with ID " + mediaId + " not found.");
        }
    }

    public void modifyMedia(String mediaId, String newTitle, int newYear) {
        Media mediaToModify = findMedia(mediaId);
        if (mediaToModify != null) {
            mediaToModify.changeTitle(newTitle);
            mediaToModify.changeYearPublished(newYear);
            System.out.println("Media modified: " + mediaToModify);
        } else {
            System.out.println("Media with ID " + mediaId + " not found.");
        }
    }
}

public class ASG4JoelLopez {
    public static void main(String[] args) {
        Manager manager = new Manager();
        manager.loadMediaData(); // Load initial media data from data file

        try (Scanner scanner = new Scanner(System.in)) {
			int choice;
			do {
			    System.out.println("MovieDVD Manager");
			    System.out.println("1. Display Media Library");
			    System.out.println("2. Add Media");
			    System.out.println("3. Remove Media");
			    System.out.println("4. Rent Media");
			    System.out.println("5. Modify Media");
			    System.out.println("6. Exit");
			    System.out.print("Enter your choice: ");
			    choice = scanner.nextInt();
			    scanner.nextLine(); // Consume the newline character

			    switch (choice) {
			        case 1:
			            manager.displayMediaLibrary();
			            break;
			        case 2:
			            System.out.print("Enter ID: ");
			            String id = scanner.nextLine();
			            System.out.print("Enter Title: ");
			            String title = scanner.nextLine();
			            System.out.print("Enter Year Published: ");
			            int yearPublished = scanner.nextInt();
			            System.out.print("Enter Size (MB): ");
			            int sizeMB = scanner.nextInt();
			            MovieDVD movieDVD = new MovieDVD(id, title, yearPublished, sizeMB);
			            manager.addMedia(movieDVD);
			            break;
			        case 3:
			            System.out.print("Enter ID of media to remove: ");
			            String mediaIdToRemove = scanner.nextLine();
			            manager.removeMedia(mediaIdToRemove);
			            break;
			        case 4:
			            System.out.print("Enter ID of media to rent: ");
			            String mediaIdToRent = scanner.nextLine();
			            manager.rentMedia(mediaIdToRent);
			            break;
			        case 5:
			            System.out.print("Enter ID of media to modify: ");
			            String mediaIdToModify = scanner.nextLine();
			            System.out.print("Enter new title: ");
			            String newTitle = scanner.nextLine();
			            System.out.print("Enter new year published: ");
			            int newYear = scanner.nextInt();
			            manager.modifyMedia(mediaIdToModify, newTitle, newYear);
			            break;
			        case 6:
			            System.out.println("Exiting MovieDVD Manager");
			            break;
			        default:
			            System.out.println("Invalid choice. Please try again.");
			            break;
			    }
			} while (choice != 6);
		}
    }
}
