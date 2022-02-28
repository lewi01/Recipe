# Recipe
menu of every meal

The recipe project allows all chefs to register first before you are allowed to post all your fourite recipes.
- An example of user registration through postman
POST/api/register
![Screenshot from 2022-02-28 15-11-15](https://user-images.githubusercontent.com/68944324/155982065-6b429c8d-e483-4787-b00e-cf51a56648a5.png)

Every user is allowed to post their recipe only if they are registered and recive a response according to the id of the recipe
-Example
POST /api/recipe/new
{
   "name": "Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
Response:

{
   "id": 1
}

Multiple users are allowed to veiw all the recipes stored.
-Example 
GET/api/recipe
[
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "date": "2020-01-02T12:11:25.034734",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "date": "2020-01-02T12:11:25.034734",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
]

Only user who post their recipes are allowed to  update & delete their recipes.
Anonymous user will recieve a forbidden http error and a massage "only user who posted the recipe are allowed to update or dalete the recipe"
Example
PUT /api/recipe/1
{
   "name": "Warming Ginger Tea",
   "category": "beverage",
   "description": "Ginger tea is a warming drink for cool weather, ...",
   "ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],
   "directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"]
}
Status code: 403 (Forbidden)
 MASSAGE: ONLY USER WHO DID POST THE RECIPE IS ALLOWED TO UPDATE IT

 DELETE /api/recipe/1
 Status code: 403 (Forbidden)
 MASSAGE: ONLY USER WHO DID POST THE RECIPE IS ALLOWED TO DELETE IT

