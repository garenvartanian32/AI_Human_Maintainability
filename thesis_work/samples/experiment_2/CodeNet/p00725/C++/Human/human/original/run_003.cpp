#include <bits/stdc++.h>

using namespace std;

using i64 = int64_t;

const i64 MOD = 1e9+7;

const i64 INF = 1e18+7;

template <typename T = i64>
struct Range{
    struct iterator{
        T value;
        const T step, last;
        const T& operator*(){return value;}
        iterator(T value, T step, T last) :
            value(step < static_cast<T>(0) ? max(last, value) : min(last, value)),
            step(step),
            last(last)
        {
        }
        iterator operator++(){value = step < static_cast<T>(0) ? max(value + step, last) : min(value + step, last); return *this;}
        bool operator!=(const iterator& x){return value != x.value;}
    };
    const T start, last, step;

    Range(const T start, const T last, const T step = static_cast<T>(1)) :
        start(start),
        last(last),
        step(step)
    {
    }

    Range(const T last) :
        start(0),
        last(last),
        step(1)
    {
    }

    iterator begin(){return iterator(start, step, last);}
    iterator end(){return iterator(last, step, last);}
};

template <typename F>
struct FixPoint{
    const F _f;
    FixPoint(F&& f) : _f(forward<F>(f)){}

    template<typename... Types>
    decltype(auto) operator()(Types&&... args) const{
        return _f(*this, forward<Types>(args)...);
    }
};

template <typename F>
static decltype(auto) makeRec(F&& f){
    return FixPoint<F>(forward<F>(f));
}

template <typename T, T Value = T()>
vector<T> makeVector(size_t x){
    return vector<T>(x, T(Value));
}

template <typename T, T Value = T(), typename... Types>
auto makeVector(size_t x, Types... args){
    return vector<decltype(makeVector<T, Value>(args...))>(x, makeVector<T, Value>(args...));
}

template <typename T = i64>
bool chmax(T& a, T b){
    if(a < b){
        a = b;
        return true;
    }
    return false;
}

template <typename T = i64>
bool chmin(T& a, T b){
    if(a > b){
        a = b;
        return true;
    }
    return false;
}

#define dump(x) fprintf(stderr, "line =%4d, name =%7s , ", __LINE__, #x); clog << "value = " << x << endl;

#define vecdump(x) fprintf(stderr, "line =%4d, name =%7s\n", __LINE__, #x); _dump_macro(x);

void _dump(int, string& x){
    clog << x << endl;
}

template <typename T>
void _dump(bool, T& x){
    clog << x << " ";
}

template <typename T, typename U = typename T::iterator>
void _dump(int, T& x){

    for(auto& elm : x)
        _dump(0, elm);

    clog << endl;
}

template <typename T>
void _dump_macro(T& x){
    _dump(0, x);
}

void _input(int, string& x){
    cin >> x;
}

template <typename T>
void _input(bool, T& x){
    cin >> x;
}

template <typename T, typename U = typename T::iterator>
void _input(int, T& x){

    for(auto& elm : x)
        _input(0, elm);
}

template <typename T>
void input_single(T& x){
    _input(0, x);
}

auto input(){}

template <typename T, typename... Types>
void input(T& value, Types&&... args){
    input_single(value);
    input(forward<Types>(args)...);
};

void _pararell_input(size_t){}

template <typename T, typename... Types>
void _pararell_input(size_t index, T& value, Types&&... args){
    input(value[index]);
    _pararell_input(index, forward<Types>(args)...);
}

template <typename... Types>
void pararell_input(size_t count, Types&&... args){
    for(const auto& i : Range<>(count))
        _pararell_input(i, forward<Types>(args)...);
}


bool solve(){

    i64 h, w;
    input(w, h);
    if(!h)
        return false;
    vector<vector<i64>> v(h, vector<i64>(w));
    input(v);
    i64 sx, sy, ex, ey;

    vector<set<i64>> sh(h), sw(w);

    for(auto& x : sh){
        x.emplace(-1);
        x.emplace(MOD);
    }
    for(auto& x : sw){
        x.emplace(-1);
        x.emplace(MOD);
    }

    for(i64 i = 0; i < h; ++i){
        for(i64 j = 0; j < w; ++j){
            if(v[i][j] == 2){
                sx = i;
                sy = j;
            }
            if(v[i][j] == 3){
                ex = i;
                ey = j;
            }
            if(v[i][j] == 1 || v[i][j] == 3){
                sh[i].emplace(j);
                sw[j].emplace(i);
            }
        }
    }

    i64 ans = MOD;

    function<void(i64,i64,i64)> f = [&](i64 x, i64 y, i64 cou){

        if(cou == 11)
            return ;

        if(!sh[x].empty()){
            auto it = sh[x].lower_bound(y);
            i64 val = *it;
            i64 val2 = *prev(it);
            if(val != MOD){
                if(x == ex && val == ey){
                    chmin(ans, cou);
                }else if(val - 1 != y){
                    sh[x].erase(val);
                    sw[val].erase(x);
                    f(x, val - 1, cou + 1);
                    sh[x].emplace(val);
                    sw[val].emplace(x);
                }
            }
            if(val2 != -1){
                val = val2;
                if(x == ex && val == ey){
                    chmin(ans, cou);
                }else if(val + 1 != y){
                    sh[x].erase(val);
                    sw[val].erase(x);
                    f(x, val + 1, cou + 1);
                    sh[x].emplace(val);
                    sw[val].emplace(x);
                }
            }
        }
        if(!sw[y].empty()){
            auto it = sw[y].lower_bound(x);
            i64 val = *it;
            i64 val2 = *prev(it);
            if(val != MOD){
                if(val == ex && y == ey){
                    chmin(ans, cou);
                }else if(val - 1 != x){
                    sw[y].erase(val);
                    sh[val].erase(y);
                    f(val - 1, y, cou + 1);
                    sw[y].emplace(val);
                    sh[val].emplace(y);
                }
            }
            if(val2 != -1){
                val = val2;
                if(val == ex && y == ey){
                    chmin(ans, cou);
                }else if(val + 1 != x){
                    sw[y].erase(val);
                    sh[val].erase(y);
                    f(val + 1, y, cou + 1);
                    sw[y].emplace(val);
                    sh[val].emplace(y);
                }
            }
        }
    };

    f(sx, sy, 1);

    cout << (ans == MOD ? -1 : ans) << endl;

    return true;
}

signed main(){

    cin.tie(0);
    ios::sync_with_stdio(false);
    cout << fixed << setprecision(20);

    while(solve());

}

