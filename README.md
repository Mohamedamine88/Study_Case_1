# Étude de Cas : Analyse Scalabilité/Performance des APIs Modernes à travers un Cas Réel de Gestion d’Hôtel

## Latence (ms)

| Taille du message | Opération | REST (ms) | SOAP (ms) | GraphQL (ms) | gRPC (ms) |
|---|---|---|---|---|---|
| 1 KB | Créer | 42 | 92 | 55 | 12 |
| 1 KB | Consulter | 38 | 88 | 52 | 10 |
| 1 KB | Modifier | 45 | 95 | 58 | 14 |
| 1 KB | Supprimer | 40 | 90 | 54 | 11 |
| 10 KB | Créer | 62 | 135 | 75 | 18 |
| 10 KB | Consulter | 58 | 128 | 70 | 16 |
| 10 KB | Modifier | 65 | 142 | 80 | 20 |
| 10 KB | Supprimer | 60 | 130 | 73 | 17 |
| 100 KB | Créer | 180 | 450 | 220 | 65 |
| 100 KB | Consulter | 175 | 440 | 210 | 60 |
| 100 KB | Modifier | 185 | 460 | 230 | 70 |
| 100 KB | Supprimer | 178 | 445 | 215 | 62 |

---

## Débit (req/s)

| Requêtes simultanées | REST (req/s) | SOAP (req/s) | GraphQL (req/s) | gRPC (req/s) |
|---|---|---|---|---|
| 10 | 450 | 200 | 320 | 1200 |
| 100 | 420 | 180 | 280 | 1100 |
| 500 | 380 | 150 | 240 | 950 |
| 1000 | 320 | 120 | 200 | 800 |

---

## Consommation CPU (%)

| Requêtes simultanées | CPU REST (%) | CPU SOAP (%) | CPU GraphQL (%) | CPU gRPC (%) |
|---|---|---|---|---|
| 10 | 8.2 | 6.5 | 7.1 | 3.2 |
| 100 | 32.5 | 24.3 | 28.4 | 12.8 |
| 500 | 68.9 | 58.2 | 62.1 | 28.4 |
| 1000 | 92.1 | 98.5 | 94.3 | 58.9 |

---

## Consommation Mémoire (MB)

| Requêtes simultanées | REST (MB) | SOAP (MB) | GraphQL (MB) | gRPC (MB) |
|---|---|---|---|---|
| 10 | 275 | 290 | 320 | 175 |
| 100 | 485 | 512 | 562 | 285 |
| 500 | 742 | 845 | 891 | 412 |
| 1000 | 950 | 1280 | 1100 | 620 |

---

## Simplicité d’Implémentation

| Critère | REST | SOAP | GraphQL | gRPC |
|---|---|---|---|---|
| Temps d'implémentation (heures) | 8 | 18 | 12 | 14 |
| Nombre de lignes de code | 132 | 158 | 156 | 187 |
| Courbe d'apprentissage (jours) | 2 | 5 | 3 | 4 |

---

## Sécurité

| Critère | REST | SOAP | GraphQL | gRPC |
|---|---|---|---|---|
| Support TLS/SSL | Oui | Oui | Oui | Oui |
| Gestion de l'authentification | HTTP Headers/OAuth | WS-Security | HTTP Headers/OAuth | mTLS/Headers |
| Résistance aux attaques | Bonne | Très bonne | Bonne | Très bonne |
