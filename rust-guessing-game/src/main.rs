use std::io; //needed to get user input/output
use std::cmp::Ordering; //for the ordering instances
use rand::RngExt; //allows a random number to be generated

//user input prompt
fn main() {
    println!("Guess the number!");

    let secret_number = rand::rng().random_range(1..=100);

    loop {
        println!("Please input your guess.");

        let mut guess: String = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

            //if the input recieved is not a number it will be converted (if possible)
        let guess: u32 = match guess.trim().parse() {
            Ok(num) => num,
            Err(_) => {
                println!("Please type a number!"); //if the input isn't a number
                continue;
            }
        };

        println!("You guessed: {guess}"); //prints the number you guessed

        //makes sure the game stops once the number is guessed 
        match guess.cmp(&secret_number) {
            Ordering::Less => println!("Too small!"),
            Ordering::Greater => println!("Too big!"),
            Ordering::Equal => {
                println!("You win!");
                break;
            }
        }
    }
}