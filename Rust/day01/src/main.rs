use std::env;
use std::fs;

fn main() {
    let args: Vec<String> = env::args().collect();

    if args.len() != 2 {
        eprintln!("Usage: {} <input_file>", args[0]);
        std::process::exit(1);
    }

    let input = fs::read_to_string(&args[1]).unwrap();
    let rotations = parse_input(input);

    let part_one = compute_part_one(&rotations);
    let part_two = compute_part_two(&rotations);

    println!("Passcode for part one: {}", part_one);
    println!("Passcode for part two: {}", part_two);
}

fn parse_input(input: String) -> Vec<i32> {
    let mut rotations: Vec<i32> = vec![];
    input.lines().map(|line| line.trim()).for_each(|line| {
        if line.is_empty() {
            return;
        }

        let direction: char = line.chars().next().unwrap();
        let amount: i32 = line[1..].parse::<i32>().unwrap();

        match direction {
            'R' => rotations.push(amount),
            'L' => rotations.push(-amount),
            _ => panic!("Invalid line found in input: {}", line),
        }
    });

    rotations
}

fn compute_part_one(rotations: &[i32]) -> i32 {
    let mut position = 50;
    let mut passcode = 0;

    rotations.iter().for_each(|rotation| {
        position = (position + rotation).rem_euclid(100);

        if position == 0 {
            passcode += 1;
        }
    });

    passcode
}

fn compute_part_two(rotations: &[i32]) -> i32 {
    let mut position: i32 = 50;
    let mut passcode: i32 = 0;

    rotations.iter().for_each(|rotation| {
        let new_pos: i32 = position + rotation;

        if *rotation > 0 {
            passcode += new_pos / 100;
        } else if *rotation < 0 {
            if new_pos == 0 {
                passcode += 1;
            } else if new_pos < 0 {
                passcode += (new_pos / 100).abs();

                if position != 0 {
                    passcode += 1;
                }
            }
        }

        position = new_pos.rem_euclid(100);
    });

    passcode
}
