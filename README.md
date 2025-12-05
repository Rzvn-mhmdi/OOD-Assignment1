# OOD-Assignment1
به نام خدا

تمرین شماره 1: آشنایی با اصول شی گرایی( موسوم به اصول SOLID)
| شماره دانشجویی | نام دانشجو |
| :---: | :---: |
| 4022170125 | فاطمه محجوب |
| 4022170126 | رضوان محمدی |

اهداف

در این آزمایش هدف بر آن است که دانشجویان با به کارگیری اصول SOLID در یک پروژه‌ی عملی ساده آشنا شوند.

مقدمه

در این آزمایش شما خواهید آموخت که چگونه می‌توانید با به کارگیری اصول SOLID، نرم‌افزارهایی را بسازید که از نظر قابلیت نگهداری و بهبود، در وضعیت مطلوبی باشند و مدیریت تغییرات در آن‌ها به آسانی میسر باشد.

## **گام ۱: افزودن دو قابلیت جدید و ثبت تغیرات**

در این گام، قابلیت‌های پیامک (SMS) و پرداخت حضوری (On-Site) به کد اولیه اضافه شدند:

الف) تغییرات مربوط به افزودن روش جدید ارسال پیام (SMS) – توسط رضوان محمدی

<div dir="rtl">

| ردیف | کلاس تغیر یافته | تغییر اعمال شده (نوع) | توضیح کوتاه در مورد تغییرات |
| :---: | :---: | :---: | :---: |
| **۱** | `Notifire.java` | ویرایش (افزودن مقدار) | افزودن مقدار **`SMS`** به `enum Notifier` برای تعریف نوع جدید اعلان در سیستم |
| **۲** | `SmsSender.java` | افزودن (کلاس جدید) | ایجاد کلاس جدید **`SmsSender`** حاوی متد `sendSms()` برای پیاده‌سازی منطق ارسال پیامک |
| **۳** | `ReservationService.java` | ویرایش (افزودن بلوک شرطی) | افزودن یک بلوک **`case SMS`** درون `switch (this.notifier)`  |

</div>

ب) تغییرات مربوط به افزودن روش جدید پرداخت (On-Site Payment) – توسط فاطمه محجوب


<div dir="rtl">

| ردیف | کلاس تغییر یافته | تغییرات اعمال شده | توضیح کوتاه در مورد تغییر |
| :---: | :---: | :---: | :---: |
| **۱** | `PaymentMethods.java` | `ONSITE` (افزوده شده به `enum`) | افزودن نوع پرداخت جدید به سیستم |
| **۲** | `PaymentProcessor.java` | `public void payOnSite(double amount)` | افزودن متد اجرای منطق پرداخت حضوری |
| **۳** | `ReservationService.java` | افزودن `case ONSITE:` و فراخوانی `payOnSite()` | افزودن شرط برای هندل کردن پرداخت حضوری در ساختار `switch` |

</div>








## گام ۲: تحلیل و وارسی برنامه از منظر تحقق و یا عدم تحقق اصول SOLID


<div dir="rtl">

| اصل | مورد | کلاس / متد | علت برقراری / نقض |
|:---:|:---:|:---:|:---:|
| **SRP** | برقراری | `Customer.java`<br>`Room.java` | فقط مسئولیت نگهداری داده‌های مشتری را دارد.<br>فقط مسئول نگهداری داده های مربوط به یک اتاق (شماره و نوع و قیمت) است. |
| **SRP** | نقض | `ReservationService.java`<br>`PaymentProcessor.java` | `ReservationService` بیش از یک مسئولیت (رزرو و مدیریت تخفیف، پرداخت و اطلاع‌رسانی) را بر عهده دارد.<br>کلاس `PaymentProcessor` مسئولیت انجام چند نوع عملیات پرداخت مجزا (کارت، نقد، پی‌پال) را بر عهده دارد. |
| **OCP** | برقراری | `Room.java`, `LuxuryRoom.java` | کلاس `LuxuryRoom` بدون نیاز به تغییر کلاس پایه `Room`، آن را گسترش داده است. |
| **OCP** | نقض | `ReservationService.java` و `PaymentProcessor.java` | در `ReservationService`، کلاس برای تغییر بسته نیست. با اضافه شدن هر روش جدید پرداخت (مثلاً ONSITE) یا اعلان (مثلاً SMS)، بلوک‌های `switch` باید تغییر کنند. <br> علاوه بر این، برای اضافه کردن تخفیف شهر جدید (مثلاً رم)، مجبور هستیم یک بلوک `else if` جدید به کد موجود اضافه کنیم. <br><br> در `PaymentProcessor` اگر بخواهیم یک روش پرداخت جدید اضافه کنیم، مجبور هستیم به کد کلاس یک متد جدید اضافه کنیم. |
| **LSP** | برقراری | `Room.java`, `LuxuryRoom.java` | نمونه کلاس فرزند (`LuxuryRoom`) می‌تواند بدون برهم زدن رفتار برنامه جایگزین نمونه کلاس والد (`Room`) در `Reservation` و سایر نقاط شود |
| **LSP** | نقض |  |  |
| **ISP** | برقراری | `MessageSender.java`, `EmailSender.java` | در حال حاضر، واسط `MessageSender` کوچک است و کلاس `EmailSender` فقط متدی را پیاده‌سازی کرده که به آن نیاز دارد |
| **ISP** | نقض | `MessageSender.java` | واسط به اندازه کافی عمومی نیست؛ نام متد `sendEmail` کلاس‌های آینده (مانند `SmsSender`) را مجبور می‌کند که متدی را پیاده‌سازی کنند که به وظیفه اصلی آن‌ها مربوط نیست |
| **DIP** | برقراری | ندارد | کلاس اصلی فاقد تزریق وابستگی از طریق واسط‌هاست |
| **DIP** | نقض | `ReservationService.java` | ماژول سطح بالا (`ReservationService`) به جای وابستگی به یک انتزاع (`Interface`)، مستقیماً شیء کلاس سطح پایین `EmailSender` را با `new` ایجاد می‌کند |
| **PLK** | برقراری | `Reservation.java` | متد `totalPrice()` برای محاسبه از فیلد `nights` (عضو مستقیم خودش) و شی مستقیم `room` استفاده می‌کند |
| **PLK** | نقض | `ReservationService.makeReservation()` | کلاس `ReservationService` به جزئیات پیاده‌سازی داخلی (ساختار فیلدهای عمومی) کلاس‌های `Reservation`, `Customer` و `Room` وابسته می‌شود و این امر کپسوله‌سازی را نقض می‌کند |
| **CRP** | برقراری | `ReservationService.java`<br>`PaymentProcessor.java`<br>`Reservation.java` | کلاس `ReservationService` برای استفاده از قابلیت‌های پرداخت، از ترکیب (`Composition`) با `PaymentProcessor` استفاده کرده است.<br>کلاس `ReservationService` برای دسترسی به داده‌ها و منطق `Reservation` (مانند محاسبه قیمت)، به جای ارث‌بری، از ترکیب استفاده کرده است.<br>کلاس `Reservation` برای استفاده از داده‌های `Room` و `Customer` به جای ارث‌بری از آن‌ها، از ترکیب استفاده کرده است و این کلاس نمونه‌ای از `Room` و `Customer` را به عنوان فیلد نگهداری می‌کند. |
| **CRP** | نقض | ندارد | در کدهای اولیه، مثال مشخصی از نقض `CRP` مشاهده نمی‌شود |

</div>

<div dir="rtl">

## گام ۳: اصلاح نقض‌ها و برقراری اصول SOLID




| اصل مربوطه | مشکل در کد اولیه | راه‌حل پیشنهادی در کد تغییر یافته |
| :---: | :---: | :---: |
| **SRP** | کلاس `PaymentProcessor.java` چندین وظیفه متفاوت (پرداخت با کارت، نقدی، پی‌پال) را در یک کلاس واحد انجام می‌داد. در نتیجه، این کلاس چندین دلیل برای تغییر داشت. | کلاس `PaymentProcessor` حذف شد و به واسط `PaymentService` و کلاس‌های تفکیک‌شده (`CardPaymentService`، `PayPalPaymentService` و `CashPaymentService`) تبدیل شد. |
| **SRP** | کلاس `ReservationService` نیز مستقیماً به جزئیات پیاده‌سازی متدهای اختصاصی (مانند `payByCard`) در `PaymentProcessor` وابسته بود. | وابستگی مستقیم به `PaymentProcessor` حذف شد. متغیر `processor` از نوع واسط `PaymentService` تعریف شد و اجرای نهایی عملیات پرداخت تنها با یک فراخوانی عمومی (`processor.processPayment(res.totalPrice());`) انجام گرفت. این کار مسئولیت اجرای دقیق پرداخت را به کلاس‌های جدید منتقل کرد. |
| **SRP** | علاوه بر این، کلاس `ReservationService` نیز چندین مسئولیت مستقل را با هم ترکیب کرده بود: مدیریت رزرو و اعمال تخفیف، مدیریت پرداخت، و مدیریت اطلاع‌رسانی (سه دلیل برای تغییر). | وظایف اضافی به کلاس‌های تخصصی منتقل شدند: اعمال تخفیف به `DiscountStrategy` و چاپ فاکتور به `InvoicePrinter`. همچنین، وابستگی به سرویس‌ها (پرداخت، اطلاع‌رسانی) از طریق متغیر payment از نوع واسط `PaymentService` و متغیر notifier از نوع واسط `MessageSender` و صدا زدن متدهای مربوطه انجام شد. این امر `ReservationService` را به یک هماهنگ‌کننده تبدیل کرد و SRP به طور کامل برقرار شد. |
| **OCP** | افزودن هر روش پرداخت جدید نیازمند تغییر مستقیم در کد کلاس و افزودن یک متد جدید به آن است. | یک `interface` به نام `PaymentService` ساختیم که متد عمومی `processPayment(double amount)` را تعریف می‌کند. <br> هر نوع پرداخت را در یک کلاس و فایل جداگانه ایجاد کردیم (`CardPaymentService`، `CashPaymentService`، `PayPalPaymentService`) و هر یک از این کلاس‌های جدید، متد `processPayment(double amount)` داخل واسط `PaymentService` را پیاده‌سازی (Implement) کردند. |
| **OCP** | کلاس `ReservationService` به شدت به جزئیات پیاده‌سازی متدهای پرداخت و اطلاع‌رسانی وابسته بود. این وابستگی از طریق استفاده از بلوک‌های `switch` برای مدیریت Enumsهای `PaymentMethods` و `Notifier` ایجاد شده بود. در نتیجه، افزودن هر روش پرداخت یا اعلان‌کننده جدید نیازمند تغییر کد داخلی متد `makeReservation` بود. | متد `makeReservation` اکنون متغیر `payment` از نوع اینترفیس `PaymentService` و متغیر `notifier` از نوع اینترفیس `MessageSender` را به عنوان پارامتر دریافت می‌کند. با این کار، کلاس `ReservationService` دیگر نیازی به دانستن نوع پرداخت یا اعلان‌کننده ندارد و فقط متدهای عمومی را فراخوانی می‌کند. این ساختار تضمین می‌کند که کلاس برای تغییر بسته و برای گسترش باز است. <br> <br> تغییرات در Main: چون بلوک‌های `switch` پرداخت و اطلاع‌رسانی از `ReservationService` حذف شدند، خطوط زیر به `Main` اضافه شدند تا ابزارهای مورد نظر را آماده کنند: <br> `PaymentService paypalProcessor = new PayPalPaymentService();` و `MessageSender emailSender = new EmailSender();` به `Main` اضافه شدند تا پرداخت (مانند پی‌پال) و اطلاع‌رسانی (مانند ایمیل) مورد نظر مشتری را برای متد `makeReservation` آماده کنند و مستقیماً تحویل دهند. |
| **OCP** | حتی پس از جدا شدن مسئولیت تخفیف از `ReservationService`، سیستم در جایی دیگر مجبور بود برای هر شهر جدید (مثل لندن یا رم) که تخفیف داشت، یک خط `else if` جدید به کد موجود اضافه کند. | برای قانون تخفیف هر شهر یک کلاس و فایل جداگانه (مثل `ParisDiscountStrategy`) ساختیم. این کار تضمین می‌کند که افزودن تخفیف جدید، فقط نیاز به ساخت یک فایل جدید داشته باشد و کد موجود تغییر نکند. <br> کلاس `CompositeDiscountService` را ساختیم تا نقش مدیر تخفیف‌ها را ایفا کند و تمام قوانین تفکیک شده را به‌صورت یک لیست در خود جای دهد و روی همه‌ی آن‌ها اجرا شود. <br> تغییرات در Main: این تغییرات صرفاً برای آماده‌سازی و تحویل این ساختار جدید به `ReservationService` انجام شدند: <br> `DiscountStrategy parisDiscount = new ParisDiscountStrategy();`: این خط شیئی را برای تخفیف پاریس می‌سازد. <br> `List<DiscountStrategy> allDiscounts = List.of(parisDiscount);`: این خط تمام قوانین فعال تخفیف را در یک لیست جمع‌آوری می‌کند. <br> `DiscountStrategy compositeDiscountService = new CompositeDiscountService(allDiscounts);`: این خط مسئول مدیریت تخفیف‌ها است تا مشخص شود با توجه به اطلاعات مشتری کدام تخفیف باید اعمال شود. <br> در نهایت، این شیء به `ReservationService` تحویل داده می‌شود. |
| **ISP** | `MessageSender` فقط برای ایمیل نامگذاری شده (`sendEmail`) و محدود به ایمیل است | تغییر نام اینترفیس به `sendMessage` برای عمومی‌تر شدن و پشتیبانی از انواع پیام‌رسانی |
| **ISP** | `EmailSender` نام متد `sendEmail` دارد که با اینترفیس همخوان نیست | تغییر نام متد به `sendMessage` در `EmailSender` برای تطابق با اینترفیس |
| **PLK** | در `ReservationService`: دسترسی مستقیم به `res.customer.city` | افزودن متد `isCity()` در `Customer` و استفاده از `res.isCustomerFromCity("Paris")` |
| **PLK** | در `ReservationService`: دسترسی مستقیم به `res.customer.name` | افزودن متد `getName()` در `Customer` و استفاده از `res.getCustomerName()` |
| **PLK** | در `ReservationService`: دسترسی مستقیم به `res.customer.email` | افزودن متد `getEmail()` در `Customer` و استفاده از `res.getCustomerEmail()` |
| **PLK** | در `ReservationService`: دسترسی مستقیم به `res.room.price` | افزودن متد `getPrice()` و `applyDiscount()` در `Room` و استفاده از `res.applyDiscountToRoom(0.9)` |
| **PLK** | در `ReservationService`: دسترسی مستقیم به `res.room.number` و `res.room.type` | افزودن متد `getNumber()` و `getType()` در `Room` و استفاده از `res.getRoomNumber()` و `res.getRoomType()` |
| **PLK** | در `Reservation`: فیلدهای `room`، `customer` و `nights` عمومی (`public`) هستند | تغییر فیلدها به `private` و اضافه کردن متدهای `getter` |
| **PLK** | در `Customer`: همه فیلدها `public` هستند | تغییر فیلدها به `private` و اضافه کردن متدهای `getter` |
| **PLK** | در `Room`: همه فیلدها `public` هستند | تغییر فیلدها به `private` و اضافه کردن متدهای `getter` |
| **DIP** | در `ReservationService`: وابستگی مستقیم به `EmailSender` (کلاس concrete) | وابستگی به اینترفیس `MessageSender` و تزریق آن از طریق `constructor` |
| **DIP** | در `ReservationService`: ایجاد نمونه مستقیم از `PaymentProcessor` | تزریق `PaymentProcessor` از طریق `constructor` |
| **DIP** | در `ReservationService`: ایجاد نمونه مستقیم از `EmailSender` | حذف ایجاد نمونه مستقیم و استفاده از `messageSender` تزریق‌شده |
| **DIP** | در `Main`: `ReservationService` بدون پارامتر ساخته می‌شود | تزریق وابستگی‌ها (`MessageSender` و `PaymentProcessor`) به `constructor` |
| **DIP** | در `Main`: استفاده مستقیم از `ReservationService` بدون تزریق وابستگی | ایجاد `EmailSender` و `PaymentProcessor` و تزریق آن‌ها به `ReservationService` |
| **DIP** | کلاس‌های `EmailSender` و `PaymentProcessor` سطح دسترسی package-private دارند | تغییر سطح دسترسی به `public` برای استفاده در لایه‌های دیگر |
| **DIP** | در `EmailSender` عدم استفاده از annotation `@Override` | اضافه کردن `@Override` برای اطمینان از صحت پیاده‌سازی |


</div>
