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
