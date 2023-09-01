delete from Ingredient_Ref;
delete from Taco;
delete from Taco_Order;

delete from Ingredient;

insert into Ingredient (id, name, type)
                values ('FLTO', 'Flour Tortilla', 'Wrap');
insert into Ingredient (id, name, type)
                values ('COTO', 'Corn Tortilla', 'Wrap');
insert into Ingredient (id, name, type)
                values ('GRBF', 'Ground Beef', 'Protein');
insert into Ingredient (id, name, type)
                values ('CARN', 'Carnitas', 'Protein');
insert into Ingredient (id, name, type)
                values ('TMTO', 'Diced Tomatoes', 'Veggies');
insert into Ingredient (id, name, type)
                values ('LETC', 'Lettuce', 'Veggies');
insert into Ingredient (id, name, type)
                values ('CHED', 'Cheddar', 'Cheese');
insert into Ingredient (id, name, type)
                values ('JACK', 'Monterrey Jack', 'Cheese');
insert into Ingredient (id, name, type)
                values ('SLSA', 'Salsa', 'Sauce');
insert into Ingredient (id, name, type)
                values ('SRCR', 'Sour Cream', 'Sauce');