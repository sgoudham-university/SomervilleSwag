DELETE FROM Product;

INSERT INTO Product (Name, Description, Price, StockLevel, ImagePath)
VALUES
    ('SomerSnow Globe', 'Be the envy of your friends with this snazzy snowscene', 20.0, 5, 'src/main/resources/product/images/derek_snowglobe.png'),
    ('Derek of Cards', 'Playing cards 100% tested and proven to improve Black Jack skills', 07.0, 5, 'src/main/resources/product/images/derek_of_cards.png'),
    ('Crushin it Crocs', 'You will be the belle of the ball in these Somerville belters', 40.0, 5, 'src/main/resources/product/images/derek_crocs.png'),
    ('Head Bobble', 'Non-stop groovin. It never gets old', 16.0, 5, 'src/main/resources/product/images/derek_headbobble.png'),
    ('SomerSpecs', 'Yo dawg we heard you like glasses', 27.0, 5, 'src/main/resources/product/images/derek_specs.png'),
    ('SomerSocks', 'Comes in various colours and all edge cases covered!', 15.0, 5, 'src/main/resources/product/images/derek_socks.png');