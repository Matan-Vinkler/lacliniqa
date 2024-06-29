# LaCliniqa

A clinics management system based on JavaFX

## Prerequisites
- IntelliJ IDEA
- MySQL
- SQLite3
- PayPal developer account

## Installation

Close this repo, navigate and run this command on Windows:
```cmd
db\initialize-db.cmd
```
Or on Linux:
```bash
db/initialize-db.sh
```

Now go [here](https://developer.paypal.com/api/rest/authentication/) and get your PayPal access-token. Create an `app.config` file:
```txt
app.name=LaCliniqa
app.access_token=<YOUR-PAYPAL-ACCESS-TOKEN>
```

Now run this project on IntelliJ.

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[Check out our license](LICENSE.md)