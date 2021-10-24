## Randevu uygulaması

Bu uygulamada android tasarım örüntülerinden MVVM kullanandım. Veri tabanı olarak Room Database üzerinde işlemler yaptım. Uygulama temel olarak kullanıcının veri tabanına bir randevuyu kaydetmesi, randevularini listelenmesi gibi islemleri yapar. işlemlerin kullanıcı arayüzünü kitlememesi için Kotlin Coroutines yapısını birkaç yerde kullandım. Sayfalar arası geçişleri Navigation Component, sayfalar arası veri alışverişi için Safe Args yapısını kullandım. 
 
## Veri tabanı yapısı 

Doktor, Hasta ve Randevular adı altınta üç ana dal ve bu dallarda ilgili bilgileri tutan tablolar tasarladim. Her bir veri kendisine ait benzersiz bir numara ile işlemlere tabi tutulur.

## XML yapısı ve kullanılan yapılar 

Her bir XML sayfası ve Kotlin sayfası ViewBinding ve DataBinding gibi yapılar ile kodlanmış. Aynı zamanda XML sayfalarında ConstraintLayout, RecyclerView sınıfını kullanarak da görüntüleme işlemlerini gerçekleştirdim.

## Projede eksik kalan kısımlar
Kullanıcının aynı randevuyu iki kere kaydetme veya ayni doktora iki kez aynı saatte randevu alma işlemi, UI.
