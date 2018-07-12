Feature: Facet Filer enabled
  Buyers who are efficiently searching with a facet filter link
  will be able to see a certain category page filtered by the facet filter

  As a seller
  I want buyers to be able to use facet filter


  Scenario Outline: Facet filter enabled
    Given I have a facet filter "<facet>" enabled for category "<category>" using link "<link>"
    Then facet filter should be enabled
    Then the top header highlighting is correct
  Examples:
    | facet    | category       | link                                                                      |
    | Lamm     | Nassfutter     | shop/de/nassfutter/c/CZ533500?q=%3Arelevance%3Aflavour%3ALamm&text=       |
    | Hase     | Probierpakete  | shop/de/probierpakete/c/CZ533594?q=%3Arelevance%3Aflavour%3AHase&text=    |
    | Fisch    | Nassfutter     | shop/de/nassfutter/c/CZ533500?q=%3Arelevance%3Aflavour%3AFisch&text=    |
    | Geflügel | Nassfutter     | shop/de/nassfutter/c/CZ533500?q=%3Arelevance%3Aflavour%3AGefl%25C3%25BCgel&text=    |
    | Bälle    | Accessoires    | shop/de/accessoires/c/CZ618462?q=%3Arelevance%3Atype_toy%3AB%25C3%25A4lle&text=    |
    | Hase     | wild-freedom   | shop/de/wild-freedom/c/CZ604816?q=%3Arelevance%3Aflavour%3AHase&text=    |
    | Huhn     | wild-freedom   | shop/de/wild-freedom/c/CZ604816?q=%3Arelevance%3Aflavour%3AHuhn&text=    |
    | Puppy    | nassfutter     | shop/de/nassfutter/c/CZ533500?q=%3Arelevance%3Aphase%3APuppy&text=    |
