package com.stephan.lib_recipe.data.local

//class DBConverter {
//
//    private val type = object :TypeToken<Resource<List<Ingredient>>>() {}.type
////    private val type = Types.newParameterizedType(List::class.java, Ingredient::class.java)
////    private val adapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter<Resource<List<Ingredient>>>(type)
////    private val stringAdapter = Moshi.Builder().add(KotlinJsonAdapterFactory()).build().adapter<String>(type)
//
//    @TypeConverter
//    fun convertIngredientListToString(list: Resource<List<Ingredient>>) : String{
//        return Gson().toJson(list, type)
//    }
//
//    @TypeConverter
//    fun convertStringToIngredientList(string: String) : Resource<List<Ingredient>>? {
//        return Gson().fromJson(string, type)
//    }
//}



//class IngredientConverter {
//    @TypeConverter
//    fun fromListToString(list: List<Ingredient?>?): String? {
//        if (list == null) {
//            return null
//        }
//        val gson = Gson()
//        val type = object : TypeToken<List<Ingredient?>?>() {}.type
//        return gson.toJson(list, type)
//    }
//    @TypeConverter
//    fun fromStringToList(string: String?): List<Ingredient?>? {
//        if (string == null) {
//            return null
//        }
//        val gson = Gson()
//        val type = object : TypeToken<List<Ingredient?>?>() {}.type
//        return gson.fromJson(string, type)
//    }
//}