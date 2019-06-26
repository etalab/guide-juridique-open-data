;; Copyright (c) 2019 DINSIC, Bastien Guerry <bastien.guerry@data.gouv.fr>
;; SPDX-License-Identifier: EPL-2.0
;; License-Filename: LICENSES/EPL-2.0.txt
(ns choices.config)

;; Allow users to send you emails with the summary?
(def mail-to "mathilde.hoang@data.gouv.fr")

;; Display help along with questions by default?
(def display-help true)

;; Default locale for UI strings
(def locale "fr-FR")

;; Customize UI strings
;; For example: (def ui-strings {:redo "Restart from scratch})
(def ui-strings
  {:mail-subject "[Guide Open Data] Demande d'aide ou d'informations complémentaires"
   :mail-body    "Bonjour,
je viens d'utiliser votre guide interactif open data et voici les
résultats que j'obtiens :
%s
Voici mes autres questions :
...
"})

;; Website header
(def header
  {:title    "Guide juridique de l'Open Data"
   :logo     "/img/logo-etalab-370x250.png"
   :color    "is-primary"
   :subtitle "Guide pour la publication des données administratives"})

;; Website footer
(def footer
  {:text    "Ce site a été réalisée par la mission [Etalab](https://www.etalab.gouv.fr), à partir du [Guide pratique de la publication en ligne et de la réutilisation des données publiques](https://www.cada.fr/lacada/consultation-publique-sur-le-guide-pratique-de-la-publication-en-ligne-et-de-la-reutilisation) rédigé par la CADA et la CNIL.  Le code source de cette page est disponible [ici](https://github.com/etalab/guide-juridique-open-data)."
   :contact "mathilde.hoang@data.gouv.fr"})

(def score {})

(def tree
  [{:name       "0"
    :text       "Présentation."
    :home-page  true
    :force-help true
    :no-summary true
    :help       "Vous êtes une administration, une collectivité, un délégataire de mission de service public ou une personne de droit public ou privé en charge d’une mission de service public.
<br/><br/>
Vous produisez ou collectez des données dans le cadre de votre mission de service public et vous vous demandez si les données que contiennent vos documents administratifs sont diffusables en open data ?
<br/><br/>
Vous êtes au bon endroit ! Il vous suffit de répondre aux questions suivantes, et vous obtiendrez une réponse simple à vos questions.
<br/><br/>
Nul besoin d’être un spécialiste de la donnée ou un juriste émérite pour vous y retrouver, ce logigramme s’adresse à tous les agents publics, quel que soit leur niveau de connaissance en la matière. Une aide lexicale vous sera fournie tout au long du logigramme afin de comprendre l’ensemble des termes utilisés. En cas de problème, vous pourrez choisir d’envoyer vos réponses à la mission Etalab et obtiendrez une aide personnalisée."
    :choices    [{:answer "Commencer"
                  :goto   "1"
                  :color  "is-success"}]}

   {:name       "1"
    :text       "Votre document administratif est-il achevé ?"
    :start-page true
    :help       "Un document administratif correspond à tout document produit ou reçu par une administration dans le cadre de sa mission de service public.
<br/><br/>
Un document administratif produit peut être une base de données contenant des informations relatives à une mission de service public, i.e. des informations publiques et communicables à tous.
<br/><br/>
Un document reçu peut être un document fourni par une administration à une autre pour les besoins de sa mission de service public.
<br/><br/>
Un document n’est pas achevé s’il s’agit d’un brouillon, d’un document de travail, d’une note préalable etc."
    :choices    [{:answer  "Oui"
                  :summary "Votre document administratif est achevé."
                  :goto    "2"
                  :color   "is-success"}
                 {:answer  "Non"
                  :summary "Votre document administratif n'est pas achevé."
                  :color   "is-warning"
                  :goto    "non"}]}

   {:name    "2"
    :text    "Le document contient-il des données couvertes par un secret légal ?"
    :help    "Les documents couverts par un secret légal sont définis par [l’article L311-5](https://www.legifrance.gouv.fr/affichCodeArticle.do?cidTexte=LEGITEXT000031366350&idArticle=LEGIARTI000031367708) et [l’article L311-6](https://www.legifrance.gouv.fr/affichCodeArticle.do;jsessionid=512397B53F42CA6350525C586C80B197.tplgfr26s_3?idArticle=LEGIARTI000037269056&cidTexte=LEGITEXT000031366350&dateTexte=20190425) du CRPA.
<br/><br/>
**L’article L311-5** porte sur les documents qui ne peuvent en aucun cas être communiqués : les documents relatifs au secret des délibérations du Gouvernement, au secret de la défense nationale ou de la sûreté de l’Etat, etc.
<br/><br/>
**L’article L311-6** porte sur les documents qui ne peuvent être communicables qu’aux intéressés : les documents qui portent atteinte à la protection de la vie privée ou au secret médical, les documents qui portent une appréciation ou un jugement de valeur sur une personne physique, les document qui font apparaître le comportement d’une personne."
    :choices [{:answer  "Oui"
               :goto    "3"
               :summary "Votre document contient des données couvertes par un secret légal."
               :color   "is-success"}
              {:answer  "Non"
               :goto    "4"
               :summary "Votre document ne contient pas de données couvertes par un secret légal."
               :color   "is-warning"}]}

   {:name    "3"
    :text    "Ces données peuvent-elles être occultées sans que cette opération n’implique des efforts disproportionnés ni que le document ne soit dénaturé ou vidé de son sens ?"
    :help    "L’occultation est un processus consistant à traiter des données couvertes par un secret légal afin d’empêcher de manière irréversible la découverte de ces secrets.
<br/><br/>
La notion « d'efforts disproportionnés » est laissée à l'appréciation de chaque administration ; on considère que le retrait d'une colonne ou d'une ligne d'une base de données ne constitue pas un effort disproportionné.
<br/><br/>
Un document est « dénaturé » ou « vidé » de son sens s'il ne contient plus de données ou si les données-clés pour la compréhension du document sont enlevées."
    :choices [{:answer  "Oui"
               :goto    "4"
               :summary ["Ces données peuvent être occultées par un traitement d'usage courant." "Vous devez occulter les données couvertes par un secret légal."]
               :color   "is-success"}
              {:answer  "Non"
               :goto    "non"
               :summary "Ces données ne peuvent pas être occultées par un traitement d'usage courant."
               :color   "is-warning"}]}

   {:name    "4"
    :text    "Le document contient-il des données à caractère personnel ?"
    :help    "Une donnée à caractère personnel correspond à toute information relative à une personne physique identifiée ou qui peut être identifiée, directement ou indirectement, par référence à un numéro d'identification ou à un ou plusieurs éléments qui lui sont propres."
    :choices [{:answer  "Oui"
               :summary "Votre document contient des données à caractère personnel."
               :goto    "5"
               :color   "is-success"}
              {:answer  "Non"
               :summary "Votre document ne contient pas de données à caractère personnel."
               :goto    "oui"
               :color   "is-warning"}]}

   {:name    "5"
    :text    "Ces données sont-elles nécessaires à l’information du public ?"
    :help    "Les catégories de documents administratifs nécessaires à l’information du public sont définis dans le Décret n° 2018-1117 du 10/12/2018. Parmi ces informations, sont notamment cités les documents relatifs aux conditions d’organisation de l’administation, de la vie économique, associative, culturelle et sportive, des professions réglementées, etc."
    :choices [{:answer  "Oui"
               :summary "Ces données sont nécessaires à l'information du public."
               :goto    "oui"
               :color   "is-success"}
              {:answer  "Non"
               :summary "Ces données ne sont pas nécessaires à l'information du public."
               :goto    "6"
               :color   "is-warning"}]}

   {:name    "6"
    :text    "Ces données peuvent-elles être anonymisées sans que cette opération n’implique des efforts disproportionnés ni que le document ne doit dénaturé ou vidé de son sens ?"
    :help    "L'anonymisation est un processus consistant à traiter des données à caractère personnel afin d'empêcher totalement et de manière irréversible l'identification d'une personne physique. L'anonymisation suppose donc qu'il n'y ait plus aucun lien possible entre l'information concernée et la personne à laquelle elle se rattache. L'identification devient alors totalement impossible. L'anonymisation doit être adaptée à chaque jeu de données.
<br/><br/>
La notion « d'efforts disproportionnés » est laissée à l'appréciation de chaque administration ; on considère que le retrait d'une colonne ou d'une ligne d'une base de données ne constitue pas un effort disproportionné.
<br/><br/>
Un document est « dénaturé » ou « vidé » de son sens s'il ne contient plus de données ou si les données-clés pour la compréhension du document sont enlevées"
    :choices [{:answer  "Oui"
               :summary ["Ces données peuvent être anonymisées." "Vous devez anonymiser les données à caractère personnel."]
               :goto    "oui"
               :color   "is-success"}
              {:answer  "Non"
               :summary "Ces données ne peuvent être anonymisées."
               :goto    "non"
               :color   "is-warning"}]}

   {:name "non"
    :text "Vous n'êtes pas tenus de publier votre document en Open data. Vous pouvez toutefois participer à la circulation des données entre administrations prévue par l'article 1er de la Loi pour une République numérique de 2016."
    :done true}

   {:name "oui"
    :text "Vous devez publier votre document en Open data sur une plateforme dédiée."
    :done true}])

