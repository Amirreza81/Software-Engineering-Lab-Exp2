<div dir="rtl">

# آزمایش دوم - سیستم پرداخت

---

## مرحله 1:

### گزارش Code Smells

#### Code Smells شناسایی‌شده

#### 1. **کلاس بزرگ (Big Class, God Class)**
- **توضیح:** کلاس `PaymentProcessor` دارای وظایف متعددی است، از جمله اعتبارسنجی، پردازش پرداخت و ثبت تراکنش، که باعث افزایش پیچیدگی و کاهش خوانایی کد می‌شود.

#### 2. **متد طولانی (Long Method)**
- **توضیح:** متد `processPayment` شامل چندین وظیفه مختلف است که باعث دشوار شدن نگهداری و درک آن می‌شود.

#### 3. **استفاده بیش از حد از Switch (Excessive Switch Statement)**
- **توضیح:** متد `processPayment` از یک `switch-case` برای مدیریت انواع مختلف پرداخت استفاده می‌کند که گسترش کد را سخت می‌کند.

#### 4. **کد تکراری (Duplicated Code)**
- **توضیح:** منطق پردازش پرداخت برای روش‌های مختلف مانند کارت اعتباری، کیف پول دیجیتال و انتقال بانکی مشابه است و باعث تکرار غیرضروری کد شده است.

#### 5. **Primitive Obsession (وابستگی بیش از حد به انواع اولیه)**
- **توضیح:** اطلاعات مشتری و پرداخت به‌صورت `Map<String, String>` ذخیره شده‌اند که باعث کاهش خوانایی و ایمنی نوع داده‌ها می‌شود.

#### 6. **Hardcoded Configuration (پیکربندی هاردکد شده)**
- **توضیح:** مقادیر مربوط به APIهای پرداخت مستقیماً در کلاس `PaymentProcessor` قرار داده شده‌اند که باعث وابستگی بالا و کاهش انعطاف‌پذیری کد می‌شود.

#### 7. **Magic Strings (رشته‌های جادویی)**
- **توضیح:** مقادیر ثابت مانند `"USD"`, `"EUR"`, `"GBP"` در کد به‌صورت رشته‌های جادویی استفاده شده‌اند. این نوع داده‌ها در صورت اشتباه تایپ، ممکن است به‌طور خاموشی منجر به خطا شوند. استفاده از **ثابت‌ها** یا **enum** برای این مقادیر، ایمنی کد را افزایش می‌دهد.

#### 8. **Lack of Unit Tests (عدم وجود تست‌های واحد)**
- **توضیح:** طراحی فعلی، وابستگی شدید به پیاده‌سازی دارد که باعث دشوار شدن تست‌نویسی می‌شود.


### نقض اصول SOLID

#### 1. **Single Responsibility Principle (SRP)**

- کلاس `PaymentProcessor` علاوه بر پردازش پرداخت، مسئولیت‌هایی مانند ثبت تراکنش‌ها و اعتبارسنجی را نیز بر عهده دارد.
- این مسئله موجب می‌شود که هر تغییری در یکی از این بخش‌ها، عملکرد کلی کلاس را تحت تأثیر قرار دهد. این موضوع نه‌تنها نگهداری کد را سخت‌تر می‌کند، بلکه احتمال بروز خطا در قسمت‌های دیگر را نیز افزایش می‌دهد.

#### 2. **Open/Closed Principle (OCP)**
- در صورت اضافه شدن یک روش پرداخت جدید (مثلاً "crypto")، باید `processPayment` و `validatePayment` تغییر کنند.
- این امر باعث تغییر مستقیم در کلاس اصلی می‌شود که منجر به افزایش احتمال بروز باگ در عملکردهای دیگر خواهد شد. در عوض، روش‌های جدید پرداخت باید بدون تغییر در کلاس `PaymentProcessor` اضافه شوند.

#### 3. **Liskov Substitution Principle (LSP)**
- به‌طور مستقیم نقض نشده است، اما پردازش پرداخت به منطق خاصی در `PaymentProcessor` وابسته است.
- در حال حاضر، استفاده از `switch-case` باعث شده است که اگر بخواهیم یک کلاس جایگزین برای پردازش پرداخت ارائه دهیم، نتواند به راحتی با منطق فعلی جایگزین شود. این موضوع باعث می‌شود که کلاس‌های جدید با محدودیت‌های غیرضروری مواجه شوند.

#### 4. **Interface Segregation Principle (ISP)**
- هیچ واسطی (Interface) تعریف نشده است، اما تمام روش‌های پرداخت باید از یک متد پردازش استفاده کنند، حتی اگر برخی از آنها به تمام پارامترها نیازی نداشته باشند.
- این موضوع باعث می‌شود که کلاس‌هایی که فقط به یک بخش از عملکرد نیاز دارند، مجبور به پیاده‌سازی متدهایی شوند که برای آن‌ها ضروری نیست. این کار باعث افزایش پیچیدگی و کاهش خوانایی کد می‌شود.

#### 5. **Dependency Inversion Principle (DIP)**
- کلاس `PaymentProcessor` مستقیماً به متدهای پردازش سطح پایین مانند `processCreditCard` وابسته است.
- این وابستگی مستقیم به پیاده‌سازی‌های سطح پایین باعث سخت‌تر شدن تغییر و جایگزینی روش‌های پردازش پرداخت می‌شود. بهتر است کلاس `PaymentProcessor` به جای وابستگی مستقیم به این متدها، به یک اینترفیس عمومی برای روش‌های پرداخت وابسته باشد.


</div>
