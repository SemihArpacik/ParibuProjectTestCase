# 🧪 Paribu Test Automation Project

Bu proje, **Paribu web arayüzü (UI)** ve **DummyJSON API** uç noktaları için hazırlanmış **Java tabanlı test otomasyon senaryolarını** içermektedir.  
Amaç, hem kullanıcı arayüzü hem de API seviyesinde senaryoların otomatik olarak test edilmesini sağlamaktır.

---

## 🚀 Teknolojiler

| Kategori | Teknoloji |
|-----------|------------|
| Programlama Dili | Java |
| UI Test | Selenium WebDriver |
| API Test | Rest Assured |
| Framework | Cucumber (Gherkin Syntax) |
| Build & Dependency | Maven |
| Raporlama | Allure Report / Extent Report |
| IDE | IntelliJ IDEA / Eclipse |

---

## 📂 Proje Yapısı


---

## 🌐 UI Test Senaryoları (@paribuUI)

### **Feature:** Paribu UI Tests

#### **Scenario: Test 1 - Market İşlemleri Doğrulaması**

#### **Scenario: Test 2 - Hatalı Giriş Mesajı Kontrolü**


---

## 🔗 API Test Senaryoları (@api)

### **Feature:** DummyJSON Login API Tests

#### **Scenario Outline: Verify login API responses for different credentials**

#### **Scenario: Verify products array length matches limit using saved token**

#### **Scenario: Update and delete first product sequentially**


---

## ▶️ Çalıştırma Talimatı

### **UI Testlerini Çalıştırmak için**
```bash
# Maven üzerinden:
mvn clean test -Dcucumber.filter.tags="@paribuUI"

# Maven üzerinden:
mvn clean test -Dcucumber.filter.tags="@api"

allure serve allure-results


