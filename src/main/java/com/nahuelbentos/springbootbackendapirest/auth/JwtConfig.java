package com.nahuelbentos.springbootbackendapirest.auth;

public class JwtConfig {
    public static final String LLAVE_SECRETA = "M1.Cl4v3.23cr3T4";

    public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEowIBAAKCAQEAnri1zyynCQ5IP7OVO3aIZv7+YxI5wpFGhgdsg8Ab9XssoRuN\n" +
            "xUujKCrPkw+kuv9+0rz5dzyOxYbLlS5nDGR/jHY145//MXWE+5vAPK3bW5GOdfsS\n" +
            "lSJVIHQN6i4PLpmeRufUh3Wnnjcw4kg95lU2+e4vC4LR0nWL+6tFO0gH8ADd1b1E\n" +
            "HpiHBHLbBLlSa7V2xsNCUkwdV8DfFqiMlMh9dHTvWrD+Vg3+cnYqgxz2aBJciEg8\n" +
            "y4qC0OzcG0it0y/PEURgGBt6yAvS+lVAP5JIn/LSV4jCCwXkvFUZufothifzJCZb\n" +
            "wdY+uD2fnBRRVqjFf/lD3fznRbqHzL3Cz4n+DwIDAQABAoIBAGR//pbc5jMkuPik\n" +
            "5FRPfJUC1t6yVqJtTGNnZf2SceGhCaRFhyIq/3dajC7JE/xX9NzJTYiDbr+B13xx\n" +
            "ckx1ghHVc8/aqkKN8BNFDpcdWq0DYFoyDRUjNRWkhn8GhRRzrrWk63yOMgFLXweY\n" +
            "yKEEyhe9CkrWnbcOOQG089RSirwbQPBUGKiCVMbBtqOK0zo4iObpuyJUhb4T2fAo\n" +
            "tw9RxGIZR2Qpvdex77nQXD7jm/2exu9sX+TKks1thT7oxj4vaIw8H1Y7J8izTFew\n" +
            "H7T9lPfyiMW2BTLoNf3m/K7AvhlYmyEdt1/4MrURggBcCakEpdtCQJxCPLinmhN+\n" +
            "6UNkKbECgYEA0IT+Io2Tpt7UQga5K5yGt5Zp3vJ7CHuuN1f+Z7F8Ib/CX08DSX0N\n" +
            "TVgEjNTj9pYuvG6ijfh80X7d4hINcsS059jI33XV8O4MVzGs1mikemryMoohWM2S\n" +
            "RGnG1hMrzGf09QZy1cfJOFccFh6tLKphYMDvUicV6fG4CZGRmgT90skCgYEAwtzl\n" +
            "fzjq+2U57wNI3+ax0LGuTktE/h3YKNiNacQSPaNtEhgYngTsCongSI63xPC8d3Ay\n" +
            "s2zyC4/7bcQf8AQAO10MgQ6vShnK15e/Buks50y89PWabJJitg47m1W8MZcr2h70\n" +
            "XSuC6A55ze5PViSOa13FB54r1kpnBUQ0RF2EnhcCgYAY9rMOI/D3W1oCBZgJlpWy\n" +
            "SPmnzVPZciUrTweQFnmL+M8mZek41RXQmqcAzUP1c4lo2b3wTT10P0IFHLAre01F\n" +
            "LHjv98WDMJydm77bdG+skD0qu8q+PO4z7o31dxoZQKDc7Ma2vVJjwloEc+u7mzJJ\n" +
            "SQ/dv/wpo9yqmJjb3ZkdsQKBgQCHnQgzFD+lGfvJtbV/7aOOpDm0z3mCiO6o7pzs\n" +
            "IMK5FwWPdCmdv7suUmo+1ORcMVZYoUMH0Chbl6bZ64Z2mbI3njhXkQq5PV4H0Nnn\n" +
            "S1wu1R8kM9F3AiGkph8hqfdBY/3m3Wgfd2qxaXGnudSJFQPxAN9gv9muVzV/Wsot\n" +
            "UhA7nQKBgCfMyQSsmkar+Krk5QyzuTp6+fugSYpLYLtnkvgq/yT9YyXJysQnnpft\n" +
            "ZqVYvyPAZcWiJuhBOrp8H4Z1Jb4dkyOSGzgFmOgKjRGYq/k3M/CO2DwSsG5P8rzw\n" +
            "xICpsevhfWM3Un82RHS+NrVS6dlPXT4cp9Y+cHLeC2GWD8t5xi1a\n" +
            "-----END RSA PRIVATE KEY-----";
    public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnri1zyynCQ5IP7OVO3aI\n" +
            "Zv7+YxI5wpFGhgdsg8Ab9XssoRuNxUujKCrPkw+kuv9+0rz5dzyOxYbLlS5nDGR/\n" +
            "jHY145//MXWE+5vAPK3bW5GOdfsSlSJVIHQN6i4PLpmeRufUh3Wnnjcw4kg95lU2\n" +
            "+e4vC4LR0nWL+6tFO0gH8ADd1b1EHpiHBHLbBLlSa7V2xsNCUkwdV8DfFqiMlMh9\n" +
            "dHTvWrD+Vg3+cnYqgxz2aBJciEg8y4qC0OzcG0it0y/PEURgGBt6yAvS+lVAP5JI\n" +
            "n/LSV4jCCwXkvFUZufothifzJCZbwdY+uD2fnBRRVqjFf/lD3fznRbqHzL3Cz4n+\n" +
            "DwIDAQAB\n" +
            "-----END PUBLIC KEY-----\n";
}
