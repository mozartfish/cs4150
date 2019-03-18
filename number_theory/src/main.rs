use std::io::{self, BufRead};

fn main() {
    //println!("Hello, World!");
    let stdin = io::stdin();
    for line in stdin.lock().lines().map(|l| l.unwrap()) {
        let input: Vec<&str> = line.split_whitespace().collect();

        if input.len() == 0 {
            continue;
        }
        let operation = String::from(input[0]);

        if operation == "gcd" {
            let a: i64 = input[1].parse::<i64>().unwrap();
            let b: i64 = input[2].parse::<i64>().unwrap();
            println!("{}", gcd(a, b));
        }
        else if operation == "exp" {
            let x: i64 = input[1].parse::<i64>().unwrap();
            let y: i64 = input[2].parse::<i64>().unwrap();
            let n: i64 = input[3].parse::<i64>().unwrap();
            println!("{}", modexp(x, y, n))
            ;
        }
        else if operation == "inverse" {
            let a: i64 = input[1].parse::<i64>().unwrap();
            let n: i64 = input[2].parse::<i64>().unwrap();
            let inverse: i64 = inverse(a, n);
            if inverse > 0 {
                println!("{}", inverse);
            }
            else {
                println!("none");
            }
        }
        else if operation == "isprime" {
            let n: i64 = input[1].parse::<i64>().unwrap();
            println!("{}", is_prime(n));
        }
        else {
            let p: i64 = input[1].parse::<i64>().unwrap();
            let q: i64 = input[2].parse::<i64>().unwrap();
            let RSA: [i64; 3] = rsa(p, q);
            println!("{} {} {}", RSA[0], RSA[1], RSA[2]);
        }
    }

    //println!("{}", g);
}
fn gcd(mut a: i64, mut b: i64) -> i64 {
    while b > 0 {
        let a_mod_b = a % b;
        a = b;
        b = a_mod_b;
    }

    return a;
}
fn modexp(x: i64, y: i64, n: i64) -> i64 {
    if y == 0 {
        return 1;
    }
    else {
        let z: i64 = modexp(x, y/2, n);
        if y % 2 == 0 {
            return ((z % n) * (z % n)) % n;
        }
        else {
            return ((((x % n) * (z % n)) % n) * (z % n)) % n;
        }
    }
}
fn ee(a: i64, b: i64) -> [i64; 3] {
    let mut foo:[i64; 3] = [0; 3];
    if b == 0 {
        foo[0] = 1;
        foo[1] = 0;
        foo[2] = a;
        return foo;
    }
    else {
        let bar:[i64; 3] = ee(b, a % b);
        foo[0] = bar[1];
        foo[1] = bar[0] - (a / b) * bar[1];
        foo[2] = bar[2];
        return foo;
    }

}
fn inverse (a: i64, n:i64) -> i64 {
    let mut foo:[i64; 3] = ee(a, n);
    if foo[2] == 1 {
        if foo[0] < 0 {
            foo[0] = foo[0] + n;
        }
        return foo[0] % n;
    }
    else {
        return -1;
    }
}
fn is_prime (n: i64) -> String {
    let a: i64 = 2;
    let b: i64 = 3;
    let c: i64 = 5;


    let mod_a: i64 = modexp(a, n-1, n) % n;
    let mod_b: i64 = modexp(b, n-1, n) % n;
    let mod_c: i64 = modexp(c, n-1, n) % n;

    if (mod_a == 1) && (mod_b == 1) && (mod_c) == 1 {
        return String::from("yes");
    }
    else {
        return String::from("no");
    }
}
fn rsa (p: i64, q: i64) -> [i64; 3] {
    let n: i64 = p * q;
    let phi: i64 = (p - 1) * (q - 1);
    let mut e: i64 = 2;
    let mut result:[i64; 3] = [0; 3];

    while gcd(e, phi) != 1 {
        e = e + 1;
    }

    let d: i64 = inverse(e, phi);
    
    result[0] = n;
    result[1] = e;
    result[2] = d;
    
    return result;
}