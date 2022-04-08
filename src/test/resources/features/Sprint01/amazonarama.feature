Feature: Amazon Arama
  @amazonarama
  Scenario: TC01_kullanici amazonda urun arar
    Given kullanıcı amazon sayfasına gider
    And kullanıcı arama kutusuna iphone yazar
    Then kullanıcı sonuc sayisini ekrana yazar

